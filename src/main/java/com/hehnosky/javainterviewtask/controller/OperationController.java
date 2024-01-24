package com.hehnosky.javainterviewtask.controller;

import com.hehnosky.javainterviewtask.dto.request.OperationRequest;
import com.hehnosky.javainterviewtask.dto.response.BankAccountResponse;
import com.hehnosky.javainterviewtask.dto.response.OperationResponse;
import com.hehnosky.javainterviewtask.service.OperationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/operation")
@Tag(name = "Operation")
@RequiredArgsConstructor
public class OperationController {

    private final OperationService operationService;

    @Operation(summary = "Get operation by id", description = "Get operation by id")
    @ApiResponse(responseCode = "200", description = "Success",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationResponse.class)))
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    @GetMapping("{id}")
    public OperationResponse findById(@PathVariable Long id){
        return operationService.findById(id);
    }

    @Operation(summary = "Get all operation", description = "Get all operation")
    @ApiResponse(responseCode = "200", description = "Success",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationResponse.class)))
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    @GetMapping
    public List<OperationResponse> findAll(){
        return operationService.findAll();
    }

    @Operation(summary = "Get all operation by account id", description = "Get all operation by account id")
    @ApiResponse(responseCode = "200", description = "Success",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationResponse.class)))
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    @GetMapping("/account/{id}")
    public List<OperationResponse> findAllByAccountId(@PathVariable Long id){
        return operationService.findAllByBankAccountId(id);
    }

    @Operation(summary = "Payment", description = "Change bank account amount add save information about this operation")
    @ApiResponse(responseCode = "200", description = "Success",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = OperationResponse.class)))
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    @PostMapping
    public OperationResponse payment(OperationRequest operationRequest){
        return operationService.payment(operationRequest);
    }
}
