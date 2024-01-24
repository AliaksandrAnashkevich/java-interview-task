package com.hehnosky.javainterviewtask.mapper;

import com.hehnosky.javainterviewtask.dto.request.BankAccountRequest;
import com.hehnosky.javainterviewtask.dto.response.BankAccountResponse;
import com.hehnosky.javainterviewtask.model.BankAccount;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankAccountMapper {

    BankAccountResponse toResponse(BankAccount bankAccount);

    BankAccount toEntity(BankAccountRequest bankAccountRequest);

}
