package com.lb.simpleWarehouse.api;

import com.lb.simpleWarehouse.db.QueryEnums;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({MethodArgumentTypeMismatchException.class})
    public ResponseEntity<Map<String, Object>> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        var params = new HashMap<String, Object>();
        params.put("message", "Wrong parameters in query");
        params.put("metrics", QueryEnums.Metric.values());
        params.put("dimensions", QueryEnums.Dimension.values());
        params.put("filters(body)", QueryEnums.Filter.values());
        return new ResponseEntity<>(
                params, new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
