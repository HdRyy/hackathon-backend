package com.kernelpanic.hackathonbackend.services;

import com.kernelpanic.hackathonbackend.DTO.*;
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
    private final CepService cepService;

    public AuthenticationResponseDTO register(RegisterRequestDTO req) {

        CepResponse cepResponse = cepService.getCepDetails(req.cep());
        Coordinates coordinates = cepResponse.getLocation().getCoordinates();

        var user = User.builder()
                .email(req.email())
                .password(passwordEncoder.encode(req.password()))
                .name(req.name())
                .cep(req.cep())
                .phoneNumber(req.phoneNumber())
                .role(Role.USER)
                .latitude(Double.parseDouble(coordinates.getLatitude()))
                .longitude(Double.parseDouble(coordinates.getLongitude()))
                .build();
        userRepository.save(user);

        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponseDTO(jwtToken, user.getId());
    }

    public AuthenticationResponseDTO login(AuthenticationRequestDTO req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.email(),
                        req.password()
                )
        );
        var user = userRepository.findByEmail(req.email())
                .orElseThrow(() -> new ResourceNotFoundException("user not found"));
        var jwtToken = jwtService.generateToken(user);
        return new AuthenticationResponseDTO(jwtToken, user.getId());
    }
}
