package com.hehnosky.javainterviewtask.dto.request;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OperationRequest {

    private BigDecimal payment;
    private Long bankAccount;
    private String type;
}
