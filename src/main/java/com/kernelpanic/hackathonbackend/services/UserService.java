package com.kernelpanic.hackathonbackend.services;

import com.kernelpanic.hackathonbackend.DTO.UserDTO;
import com.kernelpanic.hackathonbackend.DTO.mappers.UserDTOMapper;
import com.kernelpanic.hackathonbackend.entities.user.User;
import com.kernelpanic.hackathonbackend.exceptions.DuplicateResourceException;
import com.kernelpanic.hackathonbackend.exceptions.ResourceNotFoundException;
import com.kernelpanic.hackathonbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;
    private final PasswordEncoder passwordEncoder;

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userDTOMapper)
                .collect(Collectors.toList());
    }

    public UserDTO getUser(Long userId) {
        return userRepository.findById(userId)
                .map(userDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                    "user with id [%s] not found".formatted(userId)
                ));
    }

    public void updateUser(String email, String password, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "user with id [%s] not found".formatted(userId)
                ));

        if(email != null) {
            if (userRepository.existsByEmail(email)) {
                throw new DuplicateResourceException("email \"%s\" taken".formatted(email));
            }
            user.setEmail(email);
        }
        if(password != null) {
            user.setPassword(passwordEncoder.encode(password));
        }
        userRepository.save(user);
    }
    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }
}