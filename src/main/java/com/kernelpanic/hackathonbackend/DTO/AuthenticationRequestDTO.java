package com.kernelpanic.hackathonbackend.DTO;

public record AuthenticationRequestDTO(
        String email,
        String password
) {}
