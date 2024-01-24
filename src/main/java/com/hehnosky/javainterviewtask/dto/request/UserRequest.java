package com.hehnosky.javainterviewtask.dto.request;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class UserRequest {

    private String firstName;
    private String lastName;
    private String documentNumber;
    private String documentType;
}
