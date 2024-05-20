package com.phonepe.demo.model.response;

import com.phonepe.demo.model.dto.ErrorDTO;
import com.phonepe.demo.model.dto.WarningDTO;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Response implements Serializable {
    String apiRequestId;
    Integer statusCode;
    List<ErrorDTO> errors;
    List<WarningDTO> warnings;
}
