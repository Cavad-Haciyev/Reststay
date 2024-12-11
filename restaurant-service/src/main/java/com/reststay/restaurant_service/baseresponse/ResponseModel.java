package com.reststay.restaurant_service.baseresponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResponseModel {

    private LocalDateTime timeStamp;
    private Object object;
    private String message;
    private HttpStatus status;
}
