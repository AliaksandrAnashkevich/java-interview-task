package com.hehnosky.javainterviewtask.controller;

import com.hehnosky.javainterviewtask.dto.request.UserRequest;
import com.hehnosky.javainterviewtask.dto.response.UserResponse;
import com.hehnosky.javainterviewtask.service.UserService;
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
@RequestMapping("/user")
@Tag(name = "User")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Operation(summary = "Get user by id", description = "Get user by id")
    @ApiResponse(responseCode = "200", description = "Success",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class)))
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    @GetMapping("{id}")
    public UserResponse findById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @Operation(summary = "Get all user", description = "Get all user")
    @ApiResponse(responseCode = "200", description = "Success",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class)))
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    @GetMapping
    public List<UserResponse> findAll() {
        return userService.findAll();
    }

    @Operation(summary = "Create user", description = "create new user")
    @ApiResponse(responseCode = "200", description = "Success",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class)))
    @ApiResponse(responseCode = "400", description = "Conflict", content = @Content)
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    @PostMapping
    public UserResponse create(@RequestBody UserRequest userRequest) {
        return userService.create(userRequest);
    }

    @Operation(summary = "Update user", description = "Update user data")
    @ApiResponse(responseCode = "200", description = "Success",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class)))
    @ApiResponse(responseCode = "400", description = "Conflict", content = @Content)
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    @PutMapping("{id}")
    public UserResponse update(@PathVariable Long id,
                               @RequestBody UserRequest userRequest) {
        return userService.update(id, userRequest);
    }

    @Operation(summary = "Delete user", description = "Delete cascade user by id, use only for test")
    @ApiResponse(responseCode = "200", description = "Success",
        content = @Content(mediaType = "application/json", schema = @Schema(implementation = UserResponse.class)))
    @ApiResponse(responseCode = "404", description = "Not found", content = @Content)
    @DeleteMapping("{id}")
    public void deleteById(@PathVariable Long id) {
        userService.delete(id);
    }
}
