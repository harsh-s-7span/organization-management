package com.organization.management.enums;

import lombok.Getter;

@Getter
public enum ResultCode {

  SUCCESS(1001, "Success"),
  FAILED(1002, "Failed"),
  BAD_REQUEST(1003, "Bad Request"),
  CONSTRAINT_VIOLATION(1004, "Constraint Violation"),
  INVALID_OPERATION(1005, "Invalid Operation"),
  SOMETHING_WENT_WRONG(1500, "Something went wrong"),

  NOT_FOUND(2001, "Not found"),
  NO_DATA_FOUND(2002, "No data found");

  private final int value;
  private final String name;

  ResultCode(int value, String name) {
    this.value = value;
    this.name = name;
  }
}