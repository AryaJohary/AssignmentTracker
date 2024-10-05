package com.aryajohary.classroomassignment.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter @Setter
@NoArgsConstructor
public class GlobalErrorResponse {

    private HttpStatus status;
    private String message;
    private LocalDateTime timeStamp;

}
