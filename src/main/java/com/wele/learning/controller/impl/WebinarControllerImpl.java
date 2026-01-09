package com.wele.learning.controller.impl;

import com.wele.learning.controller.WebinarController;
import com.wele.learning.dto.WebinarDto;
import com.wele.learning.model.Webinar;
import com.wele.learning.service.WebinarService;
import com.wele.learning.transfer.ResponseTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WebinarControllerImpl implements WebinarController {

    @Autowired
    WebinarService webinarService;

    @Override
    public ResponseTransfer<Webinar> createWebinar(WebinarDto webinarDto) {
        return webinarService.createWebinar(webinarDto);
    }

    @Override
    public ResponseTransfer<Webinar> updateWebinar(String id, WebinarDto webinarDto) {
        return webinarService.updateWebinar(id, webinarDto);
    }

    @Override
    public ResponseTransfer<String> deleteWebinar(String id) {
        return webinarService.deleteWebinar(id);
    }

    @Override
    public ResponseTransfer<Webinar> getWebinarById(String id) {
        return webinarService.getWebinarById(id);
    }

    @Override
    public ResponseTransfer<List<Webinar>> getAllWebinars() {
        return webinarService.getAllWebinars();
    }

    @Override
    public ResponseTransfer<List<Webinar>> getWebinarsByCategory(List<String> categories) {
        return webinarService.getWebinarsByCategory(categories);
    }


}
