package com.phonepe.demo.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class WarningDTO implements Serializable {
    String warningTitle;
    String warningMessage;
    Integer warningCode;
}
