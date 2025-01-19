package com.ryu.note_backend.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import com.ryu.note_backend.dto.AccountDTO;
import com.ryu.note_backend.services.authentication.AuthenticationService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@AllArgsConstructor
public class AuthenticationController {

    AuthenticationService authenticationService;

    @PostMapping("/register")
    @Operation(parameters = {
            @Parameter(in = ParameterIn.QUERY, name = "email", required = true, schema = @Schema(type = "string")),
            @Parameter(in = ParameterIn.QUERY, name = "password", required = true, schema = @Schema(type = "string")),
            @Parameter(in = ParameterIn.QUERY, name = "name", schema = @Schema(type = "string"))
    })
    public ResponseEntity<Object> register(@RequestBody @Valid AccountDTO accountDTO) {
        return ResponseEntity.ok().body(authenticationService.register(accountDTO));
    }

    @PostMapping("/login")
    @Operation(parameters = {
            @Parameter(in = ParameterIn.QUERY, name = "email", required = true, schema = @Schema(type = "string")),
            @Parameter(in = ParameterIn.QUERY, name = "password", required = true, schema = @Schema(type = "string"))
    })
    public ResponseEntity<Object> login(@RequestBody @Valid AccountDTO accountDTO) {
        return ResponseEntity.ok()
                .body(authenticationService.login(accountDTO.getEmail(), accountDTO.getPassword()));
    }
}
