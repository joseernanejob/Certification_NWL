package com.rocketseat.certification_nlw.exceptions;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ExceptionResponse> handleInternal(Exception ex, WebRequest req) {
    ExceptionResponse response = new ExceptionResponse(
        new Date(), ex.getMessage(), req.getDescription(false));

    return new ResponseEntity<ExceptionResponse>(response, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(AppException.class)
  public ResponseEntity<ExceptionResponse> handleAppException(AppException ex, WebRequest req) {
    ExceptionResponse response = new ExceptionResponse(
        new Date(), ex.getMessage(), req.getDescription(false));

    return new ResponseEntity<ExceptionResponse>(response, ex.getStatus());
  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<ExceptionResponse> handleNotFoundException(NotFoundException ex, WebRequest req) {
    ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
    return new ResponseEntity<ExceptionResponse>(response, HttpStatus.NOT_FOUND);
  }

}
