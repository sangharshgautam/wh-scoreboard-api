package com.sangharsh.oms.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public class ApiError {
    private HttpStatus status;
    private String error;
    private Throwable throwable;
}
