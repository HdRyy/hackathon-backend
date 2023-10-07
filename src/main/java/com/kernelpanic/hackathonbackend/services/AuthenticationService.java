package com.kernelpanic.hackathonbackend.services;

import com.kernelpanic.hackathonbackend.DTO.AuthenticationRequestDTO;
import com.kernelpanic.hackathonbackend.DTO.AuthenticationResponseDTO;
import com.kernelpanic.hackathonbackend.DTO.RegisterRequestDTO;
import com.kernelpanic.hackathonbackend.entities.user.Role;
import com.kernelpanic.hackathonbackend.entities.user.User;
import com.kernelpanic.hackathonbackend.exceptions.ResourceNotFoundException;
import com.kernelpanic.hackathonbackend.repositories.UserRepository;
import com.kernelpanic.hackathonbackend.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponseDTO register(RegisterRequestDTO req) {
        var user = User.builder()
                .username(req.username())
                .password(passwordEncoder.encode(req.password()))
                .role(Role.USER)
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponseDTO(jwtToken);
    }

    public AuthenticationResponseDTO login(AuthenticationRequestDTO req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.username(),
                        req.password()
                )
        );
        var user = userRepository.findByUsername(req.username())
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponseDTO(jwtToken);
    }
}
