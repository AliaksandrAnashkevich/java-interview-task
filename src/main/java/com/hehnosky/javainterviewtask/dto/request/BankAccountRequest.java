package com.hehnosky.javainterviewtask.dto.request;

import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BankAccountRequest {

    private Long userId;
    private BigDecimal amount;
}
