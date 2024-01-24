package com.hehnosky.javainterviewtask.mapper;

import com.hehnosky.javainterviewtask.dto.request.UserRequest;
import com.hehnosky.javainterviewtask.dto.response.UserResponse;
import com.hehnosky.javainterviewtask.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "user.bankAccounts", target = "bankAccounts")
    UserResponse toResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documentType", ignore = true)
    @Mapping(target = "bankAccounts", ignore = true)
    User toEntity(UserRequest userRequest);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "documentType", ignore = true)
    @Mapping(target = "bankAccounts", ignore = true)
    User toEntity(UserRequest userRequest, @MappingTarget User user);
}
