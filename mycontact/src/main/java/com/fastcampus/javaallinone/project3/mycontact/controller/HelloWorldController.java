package com.fastcampus.javaallinone.project3.mycontact.controller;

import com.fastcampus.javaallinone.project3.mycontact.exception.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//@Controller
//@ResponseBody
@RestController
public class HelloWorldController {

    @GetMapping(value = "/api/helloWorld")
    public String helloWorld() {
        return "Hello World";
    }

    @GetMapping(value = "/api/helloException")
    public String helloException() {
        throw new RuntimeException("hello runtime exception");
    }

//    @ExceptionHandler(value = RuntimeException.class)
//    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex)
//    {
//        return new ResponseEntity<>(ErrorResponse.of(HttpStatus.INTERNAL_SERVER_ERROR,"unknown error is occurred"),HttpStatus.INTERNAL_SERVER_ERROR);
//    }
}
