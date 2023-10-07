package com.kernelpanic.hackathonbackend.DTO;

import java.util.List;

public record UserDTO(
        Long id,
        String email,
        String name,
        String cep,
        String phoneNumber
) {}
