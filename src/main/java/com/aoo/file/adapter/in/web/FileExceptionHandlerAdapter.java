package com.aoo.file.adapter.in.web;

import com.aoo.common.adapter.in.web.ErrorResponse;
import com.aoo.file.application.service.FileException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class FileExceptionHandlerAdapter {

    @ExceptionHandler(FileException.class)
    public ResponseEntity<?> handler(FileException e) {
        log.error("File Exception {} has been occurred {}", e.getError().getCode(), e.getMessage());
        return ResponseEntity
                .status(e.getError().getStatus())
                .body(ErrorResponse.of(e.getError()));
    }
}