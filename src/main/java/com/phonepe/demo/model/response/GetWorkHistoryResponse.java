package com.phonepe.demo.model.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class GetWorkHistoryResponse extends Response implements Serializable {
    List<String> issueUuidList;
}
