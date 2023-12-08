package com.organization.management.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CustomException extends RuntimeException {

  private final int errorCode;
  private final String errorMessage;

}