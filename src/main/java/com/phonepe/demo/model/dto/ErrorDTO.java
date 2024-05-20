package com.phonepe.demo.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class ErrorDTO implements Serializable {
    String errorTitle;
    String errorMessage;
    Integer errorCode;
}
