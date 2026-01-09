package com.wele.learning.time;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.Map;

@FeignClient(name = "timeClient",
        url = "http://time-service.wele-dev.svc.cluster.local:10024"
)

public interface TimeClient {

    @PostMapping("/api/v1/time/log")
    Map<String,Object> log(@RequestBody Map<String,Object> body, @RequestHeader(value = "X-Client-TZ", required = false) String clientTz);
}
