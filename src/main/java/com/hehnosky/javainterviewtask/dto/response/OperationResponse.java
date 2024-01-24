package com.hehnosky.javainterviewtask.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OperationResponse {

    private Long id;
    private LocalDateTime dateTime;
    private String type;
    private String status;
    private BigDecimal amountPayment;
    private BigDecimal amountAfter;
}
