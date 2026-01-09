package com.wele.learning.logging;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@FeignClient(name="logClient",
        url="http://log-service.wele-dev.svc.cluster.local:10023"
)

public interface LogClient {

    @PostMapping("/api/v1/logs")
    Map<String,Object> log(@RequestBody LogEntry payload);
}
