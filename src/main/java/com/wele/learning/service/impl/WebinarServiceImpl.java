package com.wele.learning.service.impl;

import com.wele.learning.dto.WebinarDto;
import com.wele.learning.enums.SpeakerRole;
import com.wele.learning.model.Speaker;
import com.wele.learning.model.SpeakerWebinarMapping;
import com.wele.learning.model.Webinar;
import com.wele.learning.repo.SpeakerRepository;
import com.wele.learning.repo.WebinarRepository;
import com.wele.learning.service.WebinarService;
import com.wele.learning.transfer.ResponseTransfer;
import com.wele.learning.utils.CodeGenerator;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class WebinarServiceImpl implements WebinarService {

    @Autowired
    WebinarRepository webinarRepository;

    @Autowired
    SpeakerRepository speakerRepository;

    @Override
    public ResponseTransfer<Webinar> createWebinar(WebinarDto webinarDto) {

        ResponseTransfer<Webinar> resTransfer = new ResponseTransfer<>();
        try {

            Webinar webinar = new Webinar();
            String code = "W" + CodeGenerator.randomAlphaNumeric();
            webinar.setWebinarCode(code);
            webinar.setTitle(webinarDto.getTitle());
            webinar.setSubtitle(webinarDto.getSubtitle());
            webinar.setDescription(webinarDto.getDescription());
            webinar.setLevel(webinarDto.getLevel());
            webinar.setActive(true);
            webinar.setDurationMinutes(webinarDto.getDurationMinutes());
            webinar.setStartDateTime(webinarDto.getStartDateTime());
            webinar.setEndDateTime(webinarDto.getEndDateTime());
            webinar.setTimezone(webinarDto.getTimezone());
            webinar.setLiveNow(true);
            webinar.setPaid(webinarDto.getPaid());
            //thumbnail
            webinar.setPrimarySpeaker(webinarDto.getPrimarySpeaker());
            webinar.setSecondarySpeakers(webinarDto.getSecondarySpeakers());

            webinar.setCategories(webinarDto.getCategories());
            webinar.setTags(webinarDto.getTags());

            String meetingLink = generateMeetingLink(code);
            webinar.setMeetingLink(meetingLink);

//        webinar.setRecordingUrl();
            webinar.setRegistrationRequired(webinarDto.getRegistrationRequired());
            webinar.setRegistrationOpenDate(webinarDto.getRegistrationOpenDate());
            webinar.setRegistrationCloseDate(webinarDto.getRegistrationCloseDate());
            webinar.setMaxRegistrations(webinarDto.getMaxRegistrations());
            webinar.setTotalRegistrations(0);
            webinar.setTotalAttendees(0);
//        webinar.setTotalAttendees();
            webinar.setCancelReason(webinarDto.getCancelReason());
//        webinar.setCreatedBy();
//        webinar.setUpdatedBy();
            webinar.setCreatedAt(LocalDateTime.now());
            webinar.setUpdatedAt(LocalDateTime.now());

            webinar.setCreatedAt(LocalDateTime.now());
            webinar.setUpdatedAt(LocalDateTime.now());

            Webinar savedWebinar = webinarRepository.save(webinar);

            // Map PRIMARY speaker
            mapSpeakerToWebinar(webinarDto.getPrimarySpeaker(), new ObjectId(savedWebinar.getId()), SpeakerRole.PRIMARY_SPEAKER);

            // Map SECONDARY speakers
            if (webinarDto.getSecondarySpeakers() != null) {
                for (ObjectId secId : webinarDto.getSecondarySpeakers()) {
                    mapSpeakerToWebinar(
                            secId,
                            new ObjectId(savedWebinar.getId()),
                            SpeakerRole.SECONDARY_SPEAKER
                    );
                }
            }

            resTransfer.setResCode(201);
            resTransfer.setBody(savedWebinar);
            resTransfer.setRemarks("Webinar created successfully");

        } catch (Exception e) {
            resTransfer.setResCode(500);
            resTransfer.setBody(null);
            resTransfer.setRemarks("Error creating webinar: " + e.getMessage());

        }
        return resTransfer;
    }


    private String generateMeetingLink(String code) {
        return "https://meet.wele.com/" + code + "-" + UUID.randomUUID();
    }

    private void mapSpeakerToWebinar(ObjectId speakerId, ObjectId webinarId, SpeakerRole role) {

        Speaker speaker = speakerRepository.findById(speakerId.toString())
                .orElseThrow(() -> new RuntimeException("Speaker not found"));

        // Initialize list if null
        if (speaker.getWebinarMappings() == null) {
            speaker.setWebinarMappings(new ArrayList<>());
        }

        // Check if already mapped to this webinar
        Optional<SpeakerWebinarMapping> mappingOpt =
                speaker.getWebinarMappings()
                        .stream()
                        .filter(m -> m.getWebinarId().equals(webinarId))
                        .findFirst();

        if (mappingOpt.isPresent()) {
            // If same webinar exists → update role (optional behavior)
            mappingOpt.get().setRole(role);

        } else {
            // New mapping
            SpeakerWebinarMapping mapping = new SpeakerWebinarMapping();
            mapping.setWebinarId(webinarId);
            mapping.setRole(role);
            speaker.getWebinarMappings().add(mapping);

        }

        speaker.setUpdatedAt(LocalDateTime.now());
        speakerRepository.save(speaker);
    }



    @Override
    public ResponseTransfer<Webinar> updateWebinar(String id, WebinarDto webinarDto) {

        ResponseTransfer<Webinar> resTransfer = new ResponseTransfer<>();

        try {

            Webinar existingWebinar = webinarRepository.findById(id).orElseThrow(() -> new RuntimeException("Webinar not found :" + id));

            existingWebinar.setTitle(webinarDto.getTitle());
            existingWebinar.setSubtitle(webinarDto.getSubtitle());
            existingWebinar.setDescription(webinarDto.getDescription());
            existingWebinar.setLevel(webinarDto.getLevel());
            existingWebinar.setActive(true);
            existingWebinar.setDurationMinutes(webinarDto.getDurationMinutes());
            existingWebinar.setStartDateTime(webinarDto.getStartDateTime());
            existingWebinar.setEndDateTime(webinarDto.getEndDateTime());
            existingWebinar.setTimezone(webinarDto.getTimezone());
            existingWebinar.setLiveNow(true);
            //thumbnail
            existingWebinar.setPrimarySpeaker(webinarDto.getPrimarySpeaker());
            existingWebinar.setSecondarySpeakers(webinarDto.getSecondarySpeakers());
            existingWebinar.setCategories(webinarDto.getCategories());
            existingWebinar.setTags(webinarDto.getTags());

            String meetingLink = generateMeetingLink(webinarDto.getWebinarCode());
            existingWebinar.setMeetingLink(meetingLink);

//        webinar.setRecordingUrl();
            existingWebinar.setRegistrationRequired(webinarDto.getRegistrationRequired());
            existingWebinar.setRegistrationOpenDate(webinarDto.getRegistrationOpenDate());
            existingWebinar.setRegistrationCloseDate(webinarDto.getRegistrationCloseDate());
            existingWebinar.setMaxRegistrations(webinarDto.getMaxRegistrations());
//        webinar.setRegisteredUsers(webinarDto.getRegisteredUsers());
//        webinar.setTotalAttendees();
            existingWebinar.setCancelReason(webinarDto.getCancelReason());
//        webinar.setCreatedBy();
//        webinar.setUpdatedBy();
            existingWebinar.setCreatedAt(LocalDateTime.now());
            existingWebinar.setUpdatedAt(LocalDateTime.now());


            existingWebinar.setLevel(webinarDto.getLevel());
            existingWebinar.setUpdatedAt(LocalDateTime.now());

            Webinar savedWebinar = webinarRepository.save(existingWebinar);

            // Map PRIMARY speaker
            mapSpeakerToWebinar(
                    webinarDto.getPrimarySpeaker(),
                    new ObjectId(savedWebinar.getId()),
                    SpeakerRole.PRIMARY_SPEAKER
            );

            // Map SECONDARY speakers
            if (webinarDto.getSecondarySpeakers() != null) {
                for (ObjectId secId : webinarDto.getSecondarySpeakers()) {
                    mapSpeakerToWebinar(
                            secId,
                            new ObjectId(savedWebinar.getId()),
                            SpeakerRole.SECONDARY_SPEAKER
                    );
                }
            }
            resTransfer.setResCode(201);
            resTransfer.setBody(savedWebinar);
            resTransfer.setRemarks("Webinar created successfully");

        } catch (Exception e) {
            resTransfer.setResCode(500);
            resTransfer.setBody(null);
            resTransfer.setRemarks("Error updating webinar: " + e.getMessage());
        }


        return resTransfer;
    }

    @Override
    public ResponseTransfer<String> deleteWebinar(String id) {

        ResponseTransfer<String> resTransfer = new ResponseTransfer<>();
        try {

            Optional<Webinar> existingWebinar = webinarRepository.findById(id);

            if (existingWebinar.isPresent()) {
                Webinar webinar = existingWebinar.get();
                webinar.setActive(false);
                webinar.setUpdatedAt(LocalDateTime.now());
                webinarRepository.save(webinar);

                resTransfer.setResCode(200);
                resTransfer.setBody("Webinar deleted successfully");
                resTransfer.setRemarks("Success");
            }

        } catch (Exception e) {
            resTransfer.setResCode(500);
            resTransfer.setBody(null);
            resTransfer.setRemarks("Error delete webinar: " + e.getMessage());
        }


        return resTransfer;
    }

    @Override
    public ResponseTransfer<Webinar> getWebinarById(String id) {

        ResponseTransfer<Webinar> resTransfer = new ResponseTransfer<>();

        try {
            Optional<Webinar> existingWebinar = webinarRepository.findById(id);

            if (existingWebinar.isPresent()) {
                resTransfer.setResCode(200);
                resTransfer.setBody(existingWebinar.get());
                resTransfer.setRemarks("Webinar fetched successfully");
            } else {
                resTransfer.setResCode(404);
                resTransfer.setBody(null);
                resTransfer.setRemarks("Webinar user not found");
            }
        } catch (Exception e) {
            resTransfer.setResCode(500);
            resTransfer.setBody(null);
            resTransfer.setRemarks("Error getWebinarById: " + e.getMessage());
        }
        return resTransfer;
    }

    @Override
    public ResponseTransfer<List<Webinar>> getAllWebinars() {

        ResponseTransfer<List<Webinar>> resTransfer = new ResponseTransfer<>();

        try {
        List<Webinar> webinars = webinarRepository.findAll();

        if (webinars != null) {
            resTransfer.setResCode(200);
            resTransfer.setBody(webinars);
            resTransfer.setRemarks("All webinars fetched successfully");
        } else {
            resTransfer.setResCode(404);
            resTransfer.setBody(null);
            resTransfer.setRemarks("Webinars not found");
        }
    } catch (Exception e) {
        resTransfer.setResCode(500);
        resTransfer.setBody(null);
        resTransfer.setRemarks("Error fetching webinar:" + e.getMessage());
    }
        return resTransfer;
    }

    @Override
    public ResponseTransfer<List<Webinar>> getWebinarsByCategory(List<String> categories) {

        ResponseTransfer<List<Webinar>> resTransfer = new ResponseTransfer<>();
        try {
            if (categories == null || categories.isEmpty()) {
                resTransfer.setResCode(400);
                resTransfer.setBody(null);
                resTransfer.setRemarks("Categories must not be empty");

            }

            List<Webinar> webinarsByCategories =
                    webinarRepository.findByCategoriesIn(categories);

            resTransfer.setResCode(200);
            resTransfer.setBody(webinarsByCategories);
            resTransfer.setRemarks("Webinars fetched successfully");

        } catch (Exception e) {
            resTransfer.setResCode(500);
            resTransfer.setBody(null);
            resTransfer.setRemarks("Error fetching webinars: " + e.getMessage());
        }


        return resTransfer;
    }
}
