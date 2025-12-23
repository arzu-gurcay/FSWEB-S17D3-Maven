package com.workintech.zoo.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@AllArgsConstructor
public class ZooException  extends RuntimeException{

    private String exceptedMessage;
    private HttpStatus httpStatus;

    public HttpStatus getStatus(){
        return httpStatus;
    }

    public String getMessage(){
        return exceptedMessage;
    }
}
