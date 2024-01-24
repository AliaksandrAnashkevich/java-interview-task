package com.hehnosky.javainterviewtask.controller;

import com.hehnosky.javainterviewtask.dto.request.BankAccountRequest;
import com.hehnosky.javainterviewtask.dto.response.BankAccountResponse;
import com.hehnosky.javainterviewtask.service.BankAccountService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
@Tag(name = "BankAccount")
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    @Operation(summary = "Get bank account by id", description = "Get bank account by id")
    @ApiResponse(responseCode = "200", description = "Success",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = BankAccountResponse.class)))
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    @GetMapping("{id}")
    public BankAccountResponse findById(@PathVariable Long id) {
        return bankAccountService.findById(id);
    }

    @Operation(summary = "Get all bank account", description = "Get all bank account")
    @ApiResponse(responseCode = "200", description = "Success",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = BankAccountResponse.class)))
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    @GetMapping
    public List<BankAccountResponse> findAll() {
        return bankAccountService.findAll();
    }

    @Operation(summary = "Get all bank account by user id", description = "Get all bank account by user id")
    @ApiResponse(responseCode = "200", description = "Success",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = BankAccountResponse.class)))
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    @GetMapping("/user/{id}")
    public List<BankAccountResponse> findAllByUserId(@PathVariable Long id) {
        return bankAccountService.findAllByUserId(id);
    }

    @Operation(summary = "Create bank account", description = "Create bank account with amount='0.00'")
    @ApiResponse(responseCode = "200", description = "Success",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = BankAccountResponse.class)))
    @ApiResponse(responseCode = "400", description = "Conflict", content = @Content)
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    @PostMapping
    public BankAccountResponse create(@RequestBody BankAccountRequest bankAccountRequest) {
        return bankAccountService.create(bankAccountRequest);
    }

    @Operation(summary = "Update bank account", description = "Update bank account amount, use only for test")
    @ApiResponse(responseCode = "200", description = "Success",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = BankAccountResponse.class)))
    @ApiResponse(responseCode = "400", description = "Conflict", content = @Content)
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    @PutMapping("{id}")
    public BankAccountResponse update(@PathVariable Long id,
                                      @RequestBody BankAccountRequest bankAccountRequest) {
        return bankAccountService.update(id, bankAccountRequest);
    }

    @Operation(summary = "Delete bank account", description = "Delete cascade bank account by id, use only for test")
    @ApiResponse(responseCode = "200", description = "Success",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = BankAccountResponse.class)))
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        bankAccountService.delete(id);
    }
}
