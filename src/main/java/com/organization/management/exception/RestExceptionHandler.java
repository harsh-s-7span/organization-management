package com.organization.management.exception;


import com.organization.management.dto.BaseResponse;
import com.organization.management.enums.ResultCode;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class RestExceptionHandler {

  @ExceptionHandler(value = CustomException.class)
  public BaseResponse<Object> customExceptionHandler(CustomException exception) {
    return new BaseResponse<>(exception.getErrorCode(), exception.getErrorMessage());
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public BaseResponse<Object> methodArgumentNotValidExceptionHandler(
      MethodArgumentNotValidException exception) {
    List<String> errorMessages =
        exception.getBindingResult().getAllErrors().stream()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .collect(Collectors.toList());
    String errorMessage = errorMessages.isEmpty() ? "Some error occurred" : errorMessages.get(0);
    return new BaseResponse<>(HttpStatus.BAD_REQUEST.value(), errorMessage);
  }

  @ExceptionHandler(value = ConstraintViolationException.class)
  public BaseResponse<Object> constrainViolationExceptionHandler(
      ConstraintViolationException exception) {
    List<String> errorMessages =
        exception.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
                .collect(Collectors.toList());
    return new BaseResponse<>(
        ResultCode.CONSTRAINT_VIOLATION.getValue(),
        ResultCode.CONSTRAINT_VIOLATION.getName(),
        errorMessages);
  }

  @ExceptionHandler(value = IllegalArgumentException.class)
  public BaseResponse<Object> illegalArgumentExceptionHandler(IllegalArgumentException exception) {
    return new BaseResponse<>(
        ResultCode.BAD_REQUEST.getValue(),
        ResultCode.BAD_REQUEST.getName(),
        exception.getMessage());
  }

  @ExceptionHandler(Exception.class)
  public BaseResponse<Object> handleException(Exception exception) {
    exception.printStackTrace();
    return new BaseResponse<>(ResultCode.SOMETHING_WENT_WRONG.getValue(), exception.getMessage());
  }
}
