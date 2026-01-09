package com.wele.learning.controller;

import com.wele.learning.dto.SpeakerDto;
import com.wele.learning.model.Speaker;
import com.wele.learning.transfer.ResponseTransfer;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v7/speakers")
public interface SpeakerController {


    @PostMapping
    ResponseTransfer<Speaker> createSpeaker(@RequestBody SpeakerDto speakerDto);

    @PutMapping("/{id}")
    ResponseTransfer<Speaker> updateSpeaker(@PathVariable String id, @RequestBody SpeakerDto speakerDto);

    @DeleteMapping("/{id}")
    ResponseTransfer<String> deleteSpeaker(@PathVariable String id);

    @QueryMapping
    ResponseTransfer<Speaker> getSpeakerById(@Argument String id);

    @QueryMapping
    ResponseTransfer<List<Speaker>> getAllSpeakers();


}


