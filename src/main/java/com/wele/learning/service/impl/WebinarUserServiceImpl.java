package com.wele.learning.service.impl;

import com.wele.learning.dto.WebinarUserDto;
import com.wele.learning.model.Webinar;
import com.wele.learning.model.WebinarUser;
import com.wele.learning.repo.WebinarRepository;
import com.wele.learning.repo.WebinarUserRepository;
import com.wele.learning.service.WebinarUserService;
import com.wele.learning.transfer.ResponseTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class WebinarUserServiceImpl implements WebinarUserService {

    @Autowired
    WebinarUserRepository webinarUserRepository;

    @Autowired
    WebinarRepository webinarRepository;

    @Override
    public ResponseTransfer<WebinarUser> createWebinarUser(WebinarUserDto webinarUserDto) {

        ResponseTransfer<WebinarUser> resTransfer = new ResponseTransfer<>();

        try {


            Optional<Webinar> webinarOpt = webinarRepository.findById(webinarUserDto.getWebinarId());

            if (webinarOpt.isEmpty()) {
                resTransfer.setResCode(404);
                resTransfer.setBody(null);
                resTransfer.setRemarks("Webinar not found");
                return resTransfer;
            }

            Webinar webinar = webinarOpt.get();

            if (webinar.getIsPaid()) {

                if (!webinarUserDto.getIsPaid()) {
                    resTransfer.setResCode(403);
                    resTransfer.setBody(null);
                    resTransfer.setRemarks("This is a paid webinar. Please complete payment to register.");
                    return resTransfer;
                }
            }

            if (webinar.getMaxRegistrations() != null && webinar.getTotalRegistrations() != null &&
                    webinar.getTotalRegistrations() >= webinar.getMaxRegistrations()) {

                resTransfer.setResCode(400);
                resTransfer.setBody(null);
                resTransfer.setRemarks("Webinar user registration limit reached");
                return resTransfer;
            }

            WebinarUser alreadyRegistered = webinarUserRepository.findFirstByEmailAndWebinarId(webinarUserDto.getEmail(), webinarUserDto.getWebinarId());

            if (alreadyRegistered != null) {
                resTransfer.setResCode(200);
                resTransfer.setBody(alreadyRegistered);
                resTransfer.setRemarks("Already someone registered with the same Email id");
                return resTransfer;
            }

            WebinarUser user = new WebinarUser();
            user.setName(webinarUserDto.getName());
            user.setEmail(webinarUserDto.getEmail());
            user.setWebinarId(webinarUserDto.getWebinarId());
            user.setActive(true);
            user.setRegistered(true);
            user.setCreatedAt(LocalDateTime.now());
            user.setUpdatedAt(LocalDateTime.now());

            WebinarUser savedUser = webinarUserRepository.save(user);

            if (webinar.getTotalRegistrations() == null) {
                webinar.setTotalRegistrations(1);
            } else {
                webinar.setTotalRegistrations(webinar.getTotalRegistrations() + 1);
            }

            webinar.setUpdatedAt(LocalDateTime.now());
            webinarRepository.save(webinar);

            resTransfer.setResCode(201);
            resTransfer.setBody(savedUser);
            resTransfer.setRemarks("Webinar user registration successful");

        } catch (Exception e) {
            resTransfer.setResCode(500);
            resTransfer.setBody(null);
            resTransfer.setRemarks("Error creating webinar user: " + e.getMessage());
        }

        return resTransfer;
    }



    @Override
    public ResponseTransfer<WebinarUser> updateWebinarUser(String id, WebinarUserDto webinarUserDto) {

        ResponseTransfer<WebinarUser> resTransfer = new ResponseTransfer<>();
        try {

            WebinarUser existingUser = webinarUserRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Webinar user not found: " + id));


            existingUser.setName(webinarUserDto.getName());
            existingUser.setEmail(webinarUserDto.getEmail());
            existingUser.setWebinarId(webinarUserDto.getWebinarId());
            existingUser.setUpdatedAt(LocalDateTime.now());


            WebinarUser savedUser = webinarUserRepository.save(existingUser);


            resTransfer.setResCode(200);
            resTransfer.setBody(savedUser);
            resTransfer.setRemarks("Webinar user updated successfully");

        } catch (Exception e) {

            resTransfer.setResCode(500);
            resTransfer.setBody(null);
            resTransfer.setRemarks("Error updating webinar user: " + e.getMessage());
        }

        return resTransfer;
    }

    @Override
    public ResponseTransfer<String> deleteWebinarUser(String id) {

        ResponseTransfer<String> resTransfer = new ResponseTransfer<>();

        try {

            Optional<WebinarUser> existingUser = webinarUserRepository.findById(id);

            if (existingUser.isPresent()) {
                WebinarUser webinaruser = existingUser.get();
                webinaruser.setActive(false);
                webinaruser.setUpdatedAt(LocalDateTime.now());
                webinarUserRepository.save(webinaruser);

                resTransfer.setResCode(200);
                resTransfer.setBody("Webinar user deleted successfully");
                resTransfer.setRemarks("Success");
            }

        } catch (Exception e) {
            resTransfer.setResCode(500);
            resTransfer.setBody(null);
            resTransfer.setRemarks("Error deleting webinar user: " + e.getMessage());
        }

        return resTransfer;
    }

    @Override
    public ResponseTransfer<WebinarUser> getWebinarUserById(String id) {
        ResponseTransfer<WebinarUser> resTransfer = new ResponseTransfer<>();

        try {
            Optional<WebinarUser> existingUser = webinarUserRepository.findById(id);

            if (existingUser.isPresent()) {
                resTransfer.setResCode(200);
                resTransfer.setBody(existingUser.get());
                resTransfer.setRemarks("Webinar user fetched successfully");
            } else {
                resTransfer.setResCode(404);
                resTransfer.setBody(null);
                resTransfer.setRemarks("Webinar user not found");
            }
        } catch (Exception e) {
            resTransfer.setResCode(500);
            resTransfer.setBody(null);
            resTransfer.setRemarks("Error fetching user:" + e.getMessage());
        }
        return resTransfer;
    }


    @Override
    public ResponseTransfer<List<WebinarUser>> getAllWebinarUsers() {
        ResponseTransfer<List<WebinarUser>> resTransfer = new ResponseTransfer<>();
        try {
            List<WebinarUser> webinarUsers = webinarUserRepository.findActiveWebinarUsers();

            if (webinarUsers != null) {
                resTransfer.setResCode(200);
                resTransfer.setBody(webinarUsers);
                resTransfer.setRemarks("All webinar users fetched successfully");
            } else {
                resTransfer.setResCode(404);
                resTransfer.setBody(null);
                resTransfer.setRemarks("Webinar user not found");
            }
        } catch (Exception e) {
            resTransfer.setResCode(500);
            resTransfer.setBody(null);
            resTransfer.setRemarks("Error fetching webinar users:" + e.getMessage());
        }
        return resTransfer;
    }
}
