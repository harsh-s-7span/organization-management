package com.organization.management.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BaseResponse<T> {

  Integer resultCode;
  String resultMessage;
  T data;

  public BaseResponse(Integer resultCode, String resultMessage) {
    this(resultCode, resultMessage, null);
  }

  public BaseResponse(Integer resultCode, String resultMessage, T data) {
    this.resultCode = resultCode;
    this.resultMessage = resultMessage;
    this.data = data;
  }
}
