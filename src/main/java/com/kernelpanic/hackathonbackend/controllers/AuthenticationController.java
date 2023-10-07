package com.kernelpanic.hackathonbackend.controllers;

import com.kernelpanic.hackathonbackend.DTO.AuthenticationRequestDTO;
import com.kernelpanic.hackathonbackend.DTO.AuthenticationResponseDTO;
import com.kernelpanic.hackathonbackend.DTO.RegisterRequestDTO;
import com.kernelpanic.hackathonbackend.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping(path = "/register")
    public ResponseEntity<AuthenticationResponseDTO> register(
            @RequestBody RegisterRequestDTO req
    ) {
        return new ResponseEntity<>(authenticationService.register(req), HttpStatus.OK);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<AuthenticationResponseDTO> login(
            @RequestBody AuthenticationRequestDTO req
    ) {
        return new ResponseEntity<>(authenticationService.login(req), HttpStatus.OK);
    }
}
