package com.wele.learning.controller.impl;

import com.wele.learning.controller.SpeakerController;
import com.wele.learning.dto.SpeakerDto;
import com.wele.learning.model.Speaker;
import com.wele.learning.service.SpeakerService;
import com.wele.learning.transfer.ResponseTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SpeakerControllerImpl implements SpeakerController {

    @Autowired
    SpeakerService speakerService;

    @Override
    public ResponseTransfer<Speaker> createSpeaker(SpeakerDto speakerDto) {
        return speakerService.createSpeaker(speakerDto);
    }

    @Override
    public ResponseTransfer<Speaker> updateSpeaker(String id, SpeakerDto speakerDto) {
        return speakerService.updateSpeaker(id, speakerDto);
    }

    @Override
    public ResponseTransfer<String> deleteSpeaker(String id) {
        return speakerService.deleteSpeaker(id);
    }

    @Override
    public ResponseTransfer<Speaker> getSpeakerById(String id) {
        return speakerService.getSpeakerById(id);
    }

    @Override
    public ResponseTransfer<List<Speaker>> getAllSpeakers() {
        return speakerService.getAllSpeakers();
    }


}
