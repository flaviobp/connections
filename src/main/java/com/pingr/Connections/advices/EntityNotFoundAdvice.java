package com.pingr.Connections.advices;

import com.pingr.Connections.exceptions.EntityValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class EntityNotFoundAdvice {
    @ResponseBody
    @ExceptionHandler(EntityValidationException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    Map<String, String> entityNotFoundHandler(EntityValidationException ex) {
        Map<String, String> resp = new HashMap<>();
        resp.put("error", ex.getMessage()); // { "error": "name is invalid" }
        return resp;
    }
}
