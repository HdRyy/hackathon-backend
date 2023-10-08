package com.kernelpanic.hackathonbackend.services;

import com.kernelpanic.hackathonbackend.DTO.CepResponse;
import com.kernelpanic.hackathonbackend.DTO.Coordinates;
import com.kernelpanic.hackathonbackend.DTO.UserDTO;
import com.kernelpanic.hackathonbackend.DTO.mappers.UserDTOMapper;
import com.kernelpanic.hackathonbackend.entities.school.School;
import com.kernelpanic.hackathonbackend.exceptions.DuplicateResourceException;
import com.kernelpanic.hackathonbackend.exceptions.ResourceNotFoundException;
import com.kernelpanic.hackathonbackend.repositories.SchoolRepository;
import com.kernelpanic.hackathonbackend.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SchoolService {

    private final SchoolRepository schoolRepository;
    private final UserRepository userRepository;
    private final UserDTOMapper userDTOMapper;
    private final CepService cepService;

    public List<School> findSchoolsWithinDistance(Long userId, double distance) {
        UserDTO user = userRepository.findById(userId)
                .map(userDTOMapper)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "user with id [%s] not found".formatted(userId)
                ));

        ArrayList<School> schoolsWithinRange = new ArrayList<School>();
        List<School> schools = schoolRepository.findAll();

        for(School school:schools){
            double earthRadius = 3958.75;
            double dLat = Math.toRadians(school.getLatitude()-user.latitude());
            double dLng = Math.toRadians(school.getLongitude()-user.longitude());
            double sindLat = Math.sin(dLat / 2);
            double sindLng = Math.sin(dLng / 2);
            double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                    * Math.cos(Math.toRadians(user.latitude())) * Math.cos(Math.toRadians(school.getLatitude()));
            double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
            double dist = earthRadius * c;

            System.out.println(dist);

            if(dist < distance){
                schoolsWithinRange.add(school);
            }
        }
        return schoolsWithinRange;
    }

    public List<School> getAllSchools() {
        return new ArrayList<>(schoolRepository.findAll());
    }

    public School getSchool(Long schoolId) {
        return schoolRepository.findById(schoolId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "school with id [%s] not found".formatted(schoolId)
                ));
    }

    public School createSchool(School school) {
        if (schoolRepository.existsByEmail(school.getEmail())) {
            throw new DuplicateResourceException("email \"%s\" taken".formatted(school.getEmail()));
        }

        CepResponse cepResponse = cepService.getCepDetails(school.getCep());
        Coordinates coordinates = cepResponse.getLocation().getCoordinates();

        School newSchool = School.builder()
                .name(school.getName())
                .email(school.getEmail())
                .cep(school.getCep())
                .latitude(Double.parseDouble(coordinates.getLatitude()))
                .longitude(Double.parseDouble(coordinates.getLongitude()))
                .build();

        return schoolRepository.save(newSchool);
    }

//    public void updateSchool(String email, String password, Long userId) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new ResourceNotFoundException(
//                        "user with id [%s] not found".formatted(userId)
//                ));
//
//        if(email != null) {
//            if (userRepository.existsByEmail(email)) {
//                throw new DuplicateResourceException("email \"%s\" taken".formatted(email));
//            }
//            user.setEmail(email);
//        }
//        if(password != null) {
//            user.setPassword(passwordEncoder.encode(password));
//        }
//        userRepository.save(user);
//    }
    public void deleteSchool(Long schoolId) {
        schoolRepository.deleteById(schoolId);
    }

}
