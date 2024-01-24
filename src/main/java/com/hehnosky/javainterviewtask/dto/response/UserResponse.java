package com.hehnosky.javainterviewtask.dto.response;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserResponse {

    private Long id;
    private String firstName;
    private String lastName;
    private String documentNumber;
    private String documentType;
    private List<BankAccountResponse> bankAccounts;

}
