package com.hehnosky.javainterviewtask.mapper;

import com.hehnosky.javainterviewtask.dto.response.OperationResponse;
import com.hehnosky.javainterviewtask.model.Operation;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OperationMapper {

    OperationResponse toResponse(Operation operation);

}
