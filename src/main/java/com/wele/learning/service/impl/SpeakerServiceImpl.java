package com.wele.learning.service.impl;

import com.wele.learning.dto.SpeakerDto;
import com.wele.learning.model.Speaker;
import com.wele.learning.model.Webinar;
import com.wele.learning.repo.SpeakerRepository;
import com.wele.learning.service.SpeakerService;
import com.wele.learning.transfer.ResponseTransfer;
import org.springframework.http.HttpStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SpeakerServiceImpl implements SpeakerService {

    @Autowired
    SpeakerRepository speakerRepository;

    @Override
    public ResponseTransfer<Speaker> createSpeaker(SpeakerDto speakerDto) {

        ResponseTransfer<Speaker> resTransfer = new ResponseTransfer<>();

        try {

            Speaker speaker = new Speaker();
            speaker.setName(speakerDto.getName());
            speaker.setDesignation(speakerDto.getDesignation());
            speaker.setCompany(speakerDto.getCompany());
//        speaker.setWebinarId(speakerDto.getWebinarId());
//        speaker.setProfileImage(speakerDto.getProfileImage());

            speaker.setActive(true);
            speaker.setCreatedAt(LocalDateTime.now());
            speaker.setUpdatedAt(LocalDateTime.now());

            Speaker savedSpeaker = speakerRepository.save(speaker);

            resTransfer.setResCode(201);
            resTransfer.setBody(savedSpeaker);
            resTransfer.setRemarks("Speaker created Successfully");

        } catch (Exception e) {
            resTransfer.setResCode(500);
            resTransfer.setBody(null);
            resTransfer.setRemarks("Error creating Speakerr: " + e.getMessage());
        }
        return resTransfer;
    }



    @Override
    public ResponseTransfer<Speaker> updateSpeaker(String id, SpeakerDto speakerDto) {

        ResponseTransfer<Speaker> resTransfer = new ResponseTransfer<>();

        try {

            Speaker existingSpeaker = speakerRepository.findById(id).orElseThrow(() -> new RuntimeException("Speaker not found :" + id));

            existingSpeaker.setName(speakerDto.getName());
            existingSpeaker.setDesignation(speakerDto.getDesignation());
            existingSpeaker.setCompany(speakerDto.getCompany());
            existingSpeaker.setProfileImage(speakerDto.getProfileImage());
//        existingSpeaker.setWebinarId(speakerDto.getWebinarId());
            existingSpeaker.setUpdatedAt(LocalDateTime.now());

            Speaker savedSpeaker = speakerRepository.save(existingSpeaker);

            resTransfer.setResCode(200);
            resTransfer.setBody(savedSpeaker);
            resTransfer.setRemarks("Speaker updating successfully");

        } catch (Exception e) {
            resTransfer.setResCode(500);
            resTransfer.setBody(null);
            resTransfer.setRemarks("Error updating speaker: " + e.getMessage());
        }


        return resTransfer;
    }

    @Override
    public ResponseTransfer<String> deleteSpeaker(String id) {

        ResponseTransfer<String> resTransfer = new ResponseTransfer<>();

        try {

            Optional<Speaker> existingSpeaker = speakerRepository.findById(id);

            if (existingSpeaker.isPresent()) {
                Speaker speaker = existingSpeaker.get();
                speaker.setActive(false);
                speaker.setUpdatedAt(LocalDateTime.now());
                speakerRepository.save(speaker);

                resTransfer.setResCode(200);
                resTransfer.setBody("speaker deleted successfully");
                resTransfer.setRemarks("Success");
            }

        } catch (Exception e) {
            resTransfer.setResCode(500);
            resTransfer.setBody(null);
            resTransfer.setRemarks("Error deleting speaker : " + e.getMessage());
        }

        return resTransfer;
    }

    @Override
    public ResponseTransfer<Speaker> getSpeakerById(String id) {

        ResponseTransfer<Speaker> resTransfer = new ResponseTransfer<>();

        try {

            Optional<Speaker> existingSpeaker = speakerRepository.findById(id);

            if (existingSpeaker.isPresent()) {
                resTransfer.setResCode(200);
                resTransfer.setBody(existingSpeaker.get());
                resTransfer.setRemarks("Speaker fetched successfully");
            } else {
                resTransfer.setResCode(404);
                resTransfer.setBody(null);
                resTransfer.setRemarks("Speaker user not found");
            }

        } catch (Exception e) {
            resTransfer.setResCode(500);
            resTransfer.setBody(null);
            resTransfer.setRemarks("Error getspeakerById : " + e.getMessage());

        }
        return resTransfer;
    }

    @Override
    public ResponseTransfer<List<Speaker>> getAllSpeakers() {

        ResponseTransfer<List<Speaker>> resTransfer = new ResponseTransfer<>();

        try {
            List<Speaker> speakers = speakerRepository.findAll();

            if (speakers != null) {
                resTransfer.setResCode(200);
                resTransfer.setBody(speakers);
                resTransfer.setRemarks("All Speakers fetched successfully");
            } else {
                resTransfer.setResCode(404);
                resTransfer.setBody(null);
                resTransfer.setRemarks("speakers not found");
            }
        } catch (Exception e) {
            resTransfer.setResCode(500);
            resTransfer.setBody(null);
            resTransfer.setRemarks("Error getAllspeakers : " + e.getMessage());
        }


            return resTransfer;
        }


}
