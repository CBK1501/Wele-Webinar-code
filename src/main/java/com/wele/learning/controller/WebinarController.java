package com.wele.learning.controller;

import com.wele.learning.dto.WebinarDto;
import com.wele.learning.model.Webinar;
import com.wele.learning.transfer.ResponseTransfer;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v7/webinars")
public interface WebinarController {

    @PostMapping
    ResponseTransfer<Webinar> createWebinar(@RequestBody WebinarDto webinarDto);

    @PutMapping("/{id}")
    ResponseTransfer<Webinar> updateWebinar(@PathVariable String id,@RequestBody WebinarDto webinarDto);

    @DeleteMapping("/{id}")
    ResponseTransfer<String> deleteWebinar(@PathVariable String id);

    @QueryMapping
    ResponseTransfer<Webinar> getWebinarById(@Argument String id);

    @QueryMapping
    ResponseTransfer<List<Webinar>> getAllWebinars();

    @QueryMapping
    ResponseTransfer<List<Webinar>> getWebinarsByCategory(@Argument List<String> categories);
}
