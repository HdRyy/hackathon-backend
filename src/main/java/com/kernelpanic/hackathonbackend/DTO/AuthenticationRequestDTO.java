package com.kernelpanic.hackathonbackend.DTO;

public record AuthenticationRequestDTO(
        String username,
        String password
) {}
