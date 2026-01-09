package com.wele.learning.service;

import com.wele.learning.dto.WebinarUserDto;
import com.wele.learning.transfer.ResponseTransfer;
import org.springframework.http.ResponseEntity;

public interface WebinarAttendanceService {


    ResponseTransfer<String> joinWebinar(WebinarUserDto webinarUserDto);

    ResponseTransfer<String> leaveWebinar(WebinarUserDto webinarUserDto);
}
