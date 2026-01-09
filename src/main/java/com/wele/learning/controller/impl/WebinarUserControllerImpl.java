package com.wele.learning.controller.impl;

import com.wele.learning.controller.WebinarUserController;
import com.wele.learning.dto.WebinarUserDto;
import com.wele.learning.model.WebinarUser;
import com.wele.learning.service.WebinarUserService;
import com.wele.learning.transfer.ResponseTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.Response;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebinarUserControllerImpl implements WebinarUserController {

    @Autowired
    WebinarUserService webinarUserService;

    @Override
    public ResponseTransfer<WebinarUser> createWebinarUser(WebinarUserDto webinarUserDto) {
        return webinarUserService.createWebinarUser(webinarUserDto);
    }

    @Override
    public ResponseTransfer<WebinarUser> updateWebinarUser(String id, WebinarUserDto webinarUserDto) {
        return webinarUserService.updateWebinarUser(id, webinarUserDto);
    }

    @Override
    public ResponseTransfer<String> deleteWebinarUser(String id) {
        return webinarUserService.deleteWebinarUser(id);
    }

    @Override
    public ResponseTransfer<WebinarUser> getWebinarUserById(String id) {
        return webinarUserService.getWebinarUserById(id);
    }

    @Override
    public ResponseTransfer<List<WebinarUser>> getAllWebinarUser() {
       return webinarUserService.getAllWebinarUsers();
    }


}
