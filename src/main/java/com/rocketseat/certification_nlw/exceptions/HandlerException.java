package com.rocketseat.certification_nlw.exceptions;

import java.util.Date;

import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HandlerException extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
      HttpHeaders headers,
      HttpStatusCode status, WebRequest req) {
    ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
    return new ResponseEntity<>(response,
        HttpStatus.BAD_REQUEST);
  }

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

  @ExceptionHandler(InvalidDataAccessApiUsageException.class)
  public ResponseEntity<ExceptionResponse> handleInvalidDataException(InvalidDataAccessApiUsageException ex,
      WebRequest req) {
    ExceptionResponse response = new ExceptionResponse(new Date(), ex.getMessage(), req.getDescription(false));
    return new ResponseEntity<ExceptionResponse>(response, HttpStatus.BAD_REQUEST);
  }

}
