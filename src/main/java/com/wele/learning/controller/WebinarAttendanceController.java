package com.wele.learning.controller;

import com.wele.learning.dto.WebinarUserDto;
import com.wele.learning.transfer.ResponseTransfer;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v7/webinarsAttendance")
public interface WebinarAttendanceController {

    @PostMapping("/join")
    ResponseTransfer<String> joinWebinar(@RequestBody WebinarUserDto webinarUserDto);

    @PostMapping("/leave")
    ResponseTransfer<String> leaveWebinar(@RequestBody WebinarUserDto webinarUserDto);
}
