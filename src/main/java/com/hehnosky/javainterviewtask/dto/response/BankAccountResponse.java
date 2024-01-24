package com.hehnosky.javainterviewtask.dto.response;


import java.math.BigDecimal;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class BankAccountResponse {

    private Long id;
    private String code;
    private BigDecimal amount;

}
