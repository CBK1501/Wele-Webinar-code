package com.wele.learning.service;

import com.wele.learning.dto.WebinarUserDto;
import com.wele.learning.model.WebinarUser;
import com.wele.learning.transfer.ResponseTransfer;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WebinarUserService {
    ResponseTransfer<WebinarUser> createWebinarUser(WebinarUserDto webinarUserDto);

    ResponseTransfer<WebinarUser> updateWebinarUser(String id, WebinarUserDto webinarUserDto);

    ResponseTransfer<String> deleteWebinarUser(String id);

    ResponseTransfer<WebinarUser> getWebinarUserById(String id);

    ResponseTransfer<List<WebinarUser>> getAllWebinarUsers();

}
