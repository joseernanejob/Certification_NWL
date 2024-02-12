package com.rocketseat.certification_nlw.exceptions;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AppException extends RuntimeException {

  private final HttpStatus status;

  public AppException(String string, HttpStatus status) {
    super(string);
    this.status = status;
  }

}
