package com.kernelpanic.hackathonbackend.DTO;

import java.time.LocalDate;

public record RegisterRequestDTO(
        String email,
        String password,
        String name,
        String cep,
        String phoneNumber


) {}
