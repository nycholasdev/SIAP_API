package com.luannycholas.controller;

import com.luannycholas.exceptions.ExceptionDTO;
import com.luannycholas.models.User;
import com.luannycholas.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/user")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Operation(
            summary = "Create new user",
            method = "POST",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content =
            @Content(schema = @Schema(implementation = User.class))),
            responses = {
                    @ApiResponse(
                            description = "User Created",
                            responseCode = "201",
                            content = { @Content}),
                    @ApiResponse(
                            description = "One or more fields are empty or have invalid values",
                            responseCode = "422",
                    content = { @Content(schema = @Schema(implementation = ExceptionDTO.class))})
            }
    )

    @PostMapping(path = "/")
    public ResponseEntity<String> insert(@RequestBody @Validated User user) {
        userService.createUserService(user);
        String successMessage = "User created successfully";
        return new ResponseEntity<>(successMessage,HttpStatus.CREATED);
    }

    @Operation(
        summary = "Get users",
        method = "GET",
        responses = {
            @ApiResponse(
                description = "Users found.",
                responseCode = "200",
                content = { @Content(schema = @Schema(implementation = User.class))}),
            @ApiResponse(
                description = "There are no users registered in the bank.",
                responseCode = "404",
                content = { @Content(schema = @Schema(implementation = ExceptionDTO.class))})
        }
    )

    @GetMapping("/")
    public ResponseEntity<Map<String, Object>> getAllUsers() {
        List<User> users = userService.getAllUsersService();
        Map<String, Object> response = new HashMap<>();
        
        if (users.isEmpty()) {
            response.put("message", "No users found.");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        } else {
            response.put("message", "Users retrieved successfully.");
            response.put("users", users);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }



    @Operation(
        summary = "Get users",
        method = "GET",
        responses = {
            @ApiResponse(
                description = "Users not found.",
                responseCode = "200",
                content = { @Content(schema = @Schema(implementation = User.class))}),
            @ApiResponse(
                description = "There are no user registered in the bank.",
                responseCode = "404",
                content = { @Content(schema = @Schema(implementation = ExceptionDTO.class))})
        }
    )

    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getUserById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        
        User user = userService.getUserByIdService(id);
        
        if (user == null) {
            response.put("message", "User not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.put("message", "User retrieved successfully.");
            response.put("user", user);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
    }


    @Operation(
        summary = "Delete users",
        method = "DELETE",
        responses = {
            @ApiResponse(
                description = "Users not found.",
                responseCode = "200",
                content = { @Content(schema = @Schema(implementation = User.class))}),
            @ApiResponse(
                description = "There are no user registered in the bank.",
                responseCode = "404",
                content = { @Content(schema = @Schema(implementation = ExceptionDTO.class))})
        }
    )

    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteUserById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        
        boolean isDeleted = userService.deleteUserByIdService(id);
        
        if (!isDeleted) {
            response.put("message", "User not found.");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        } else {
            response.put("message", "User deleted successfully.");
            return new ResponseEntity<>(response, HttpStatus.NO_CONTENT);
        }
    }

}