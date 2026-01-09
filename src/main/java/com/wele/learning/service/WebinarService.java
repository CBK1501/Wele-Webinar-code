package com.wele.learning.service;

import com.wele.learning.dto.WebinarDto;
import com.wele.learning.model.Webinar;
import com.wele.learning.transfer.ResponseTransfer;

import java.util.List;

public interface WebinarService {
    ResponseTransfer<Webinar> createWebinar(WebinarDto webinarDto);

    ResponseTransfer<Webinar> updateWebinar(String id, WebinarDto webinarDto);

    ResponseTransfer<String> deleteWebinar(String id);

    ResponseTransfer<Webinar> getWebinarById(String id);

    ResponseTransfer<List<Webinar>> getAllWebinars();

    ResponseTransfer<List<Webinar>> getWebinarsByCategory(List<String> categories);
}
