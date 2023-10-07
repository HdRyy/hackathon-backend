package com.kernelpanic.hackathonbackend.DTO.mappers;

import com.kernelpanic.hackathonbackend.DTO.UserDTO;
import com.kernelpanic.hackathonbackend.entities.user.User;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class UserDTOMapper implements Function<User, UserDTO> {

    @Override
    public UserDTO apply(User user){
        return new UserDTO(
                user.getId(),
                user.getUsername()
        );
    }
}
