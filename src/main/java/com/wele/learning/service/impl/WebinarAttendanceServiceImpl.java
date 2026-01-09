package com.wele.learning.service.impl;

import com.wele.learning.dto.WebinarUserDto;
import com.wele.learning.model.Webinar;
import com.wele.learning.model.WebinarUser;
import com.wele.learning.repo.WebinarRepository;
import com.wele.learning.repo.WebinarUserRepository;
import com.wele.learning.service.WebinarAttendanceService;
import com.wele.learning.transfer.ResponseTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class WebinarAttendanceServiceImpl implements WebinarAttendanceService {

    @Autowired
    WebinarUserRepository webinarUserRepository;

    @Autowired
    WebinarRepository webinarRepository;

    @Override
    public ResponseTransfer<String> joinWebinar(WebinarUserDto webinarUserDto) {

        ResponseTransfer<String> resTransfer = new ResponseTransfer<>();

        try {
            WebinarUser webinarUser = webinarUserRepository.findFirstByEmailAndWebinarId(webinarUserDto.getEmail(), webinarUserDto.getWebinarId());

            if (webinarUser == null) {
                resTransfer.setResCode(404);
                resTransfer.setBody(null);
                resTransfer.setRemarks("User not registered for this webinar");
            }

            // First join only
            if (webinarUser.getJoinedAt() == null) {
                webinarUser.setJoinedAt(LocalDateTime.now());
                webinarUser.setAttended(true);

                // Count attendee ONLY once
                updateTotalAttendeesCountAndViewsCount(webinarUser.getWebinarId());

            }

            webinarUser.setUpdatedAt(LocalDateTime.now());

            webinarUserRepository.save(webinarUser);

            resTransfer.setResCode(200);
            resTransfer.setBody("Joined webinar successfully");
            resTransfer.setRemarks("User joined the webinar");

        } catch (Exception e) {
            resTransfer.setResCode(500);
            resTransfer.setBody(null);
            resTransfer.setRemarks("Error join Webinar: " + e.getMessage());
        }


        return resTransfer;
    }

    @Override
    public ResponseTransfer<String> leaveWebinar(WebinarUserDto webinarUserDto) {

        ResponseTransfer<String> resTransfer = new ResponseTransfer<>();

        try {

            WebinarUser webinarUser = webinarUserRepository.findFirstByEmailAndWebinarId(webinarUserDto.getEmail(), webinarUserDto.getWebinarId());

            if (webinarUser == null) {
                resTransfer.setResCode(404);
                resTransfer.setBody(null);
                resTransfer.setRemarks("User not found for this webinar");
            }

            webinarUser.setLeftAt(LocalDateTime.now());
            webinarUser.setUpdatedAt(LocalDateTime.now());

            webinarUserRepository.save(webinarUser);

            resTransfer.setResCode(200);
            resTransfer.setBody("leave webinar successfully");
            resTransfer.setRemarks("leave joined the webinar");

        } catch (Exception e) {
            resTransfer.setResCode(500);
            resTransfer.setBody(null);
            resTransfer.setRemarks("Error leave Webinar: " + e.getMessage());
        }

        return resTransfer;
    }

    private void updateTotalAttendeesCountAndViewsCount(String webinarId) {

//        long totalAttendees = webinarUserRepository
//                .countByWebinarIdAndRegisteredTrue(webinarId);

        Webinar webinar = webinarRepository.findById(webinarId)
                .orElseThrow(() -> new RuntimeException("Webinar not found"));


        Integer attendeesCount = webinar.getTotalAttendees();

        if (attendeesCount == null) {
            attendeesCount = 0;
        }
        webinar.setTotalAttendees(attendeesCount + 1);


        Integer viewsCount = webinar.getViewsCount();

        if (viewsCount == null) {
            viewsCount = 0;
        }
        webinar.setViewsCount(viewsCount + 1);

        webinar.setUpdatedAt(LocalDateTime.now());
        webinarRepository.save(webinar);
    }

}
