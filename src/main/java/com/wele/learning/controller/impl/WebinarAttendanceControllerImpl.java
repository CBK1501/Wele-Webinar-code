package com.wele.learning.controller.impl;

import com.wele.learning.controller.WebinarAttendanceController;
import com.wele.learning.dto.WebinarUserDto;
import com.wele.learning.service.WebinarAttendanceService;
import com.wele.learning.transfer.ResponseTransfer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WebinarAttendanceControllerImpl implements WebinarAttendanceController {

    @Autowired
    WebinarAttendanceService webinarAttendanceService;

    @Override
    public ResponseTransfer<String> joinWebinar(WebinarUserDto webinarUserDto) {
        return webinarAttendanceService.joinWebinar(webinarUserDto);
    }

    @Override
    public ResponseTransfer<String> leaveWebinar(WebinarUserDto webinarUserDto) {
        return webinarAttendanceService.leaveWebinar(webinarUserDto);
    }
}
