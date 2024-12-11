package com.reststay.user_service.baseresponse;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
public class ResponseModelService {


    public static ResponseEntity<Object> responseBuilder(LocalDateTime timeStamp,
                                                         Object responseObject,
                                                         String message, HttpStatus status
    ) {
        ResponseModel response = ResponseModel.builder()
                .timeStamp(timeStamp)
                .object(responseObject)
                .message(message)
                .status(status)
                .build();
        return new ResponseEntity<>(response, status);

    }

    public static ResponseEntity<List<Object>> responseBuilderaList(LocalDateTime timeStamp,
                                                                    Object responseObject,
                                                                    String message, HttpStatus status
    ) {
        List<Object> list = new ArrayList<>();
        ResponseModel response = ResponseModel.builder()
                .timeStamp(timeStamp)
                .object(responseObject)
                .message(message)
                .status(status)
                .build();
        list.add(response);
        return new ResponseEntity<>(list, status);

    }
}