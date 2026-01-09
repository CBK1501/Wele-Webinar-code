package com.wele.learning.controller;

import com.wele.learning.dto.WebinarUserDto;
import com.wele.learning.model.WebinarUser;
import com.wele.learning.transfer.ResponseTransfer;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v7/webinarusers")
public interface WebinarUserController {

    @PostMapping
    ResponseTransfer<WebinarUser> createWebinarUser(@RequestBody WebinarUserDto webinarUserDto);

    @PutMapping("/{id}")
    ResponseTransfer<WebinarUser> updateWebinarUser(@PathVariable String id, @RequestBody WebinarUserDto webinarUserDto);

    @DeleteMapping("/{id}")
    ResponseTransfer<String> deleteWebinarUser(@PathVariable String id);

    @QueryMapping
    ResponseTransfer<WebinarUser> getWebinarUserById(@Argument String id);

    @QueryMapping
    ResponseTransfer<List<WebinarUser>> getAllWebinarUser();


}
