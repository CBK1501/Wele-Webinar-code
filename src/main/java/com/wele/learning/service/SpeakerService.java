package com.wele.learning.service;

import com.wele.learning.dto.SpeakerDto;
import com.wele.learning.model.Speaker;
import com.wele.learning.transfer.ResponseTransfer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SpeakerService {
    ResponseTransfer<Speaker> createSpeaker(SpeakerDto speakerDto);

    ResponseTransfer<Speaker> updateSpeaker(String id, SpeakerDto speakerDto);

    ResponseTransfer<String> deleteSpeaker(String id);

    ResponseTransfer<Speaker> getSpeakerById(String id);

    ResponseTransfer<List<Speaker>> getAllSpeakers();
}
