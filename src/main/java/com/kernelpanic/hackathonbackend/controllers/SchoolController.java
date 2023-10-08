package com.kernelpanic.hackathonbackend.controllers;

import com.kernelpanic.hackathonbackend.DTO.UserDTO;
import com.kernelpanic.hackathonbackend.entities.school.School;
import com.kernelpanic.hackathonbackend.services.SchoolService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping(path = "api/v1/school")
public class SchoolController {

    private final SchoolService schoolService;

    @GetMapping("/distance/{userId}")
    public ResponseEntity<List<School>> findSchoolsWithinDistance(
            @PathVariable("userId") Long userId,
            @RequestParam double distance) {
        return new ResponseEntity<>(schoolService.findSchoolsWithinDistance(userId, distance), HttpStatus.OK) ;
    }

    @GetMapping(path="/")
    public ResponseEntity<List<School>> getAllSchools() {
        return new ResponseEntity<>(schoolService.getAllSchools(), HttpStatus.OK) ;
    }

    @GetMapping(path = "/{schoolId}")
    public ResponseEntity<School> getSchool(@PathVariable("schoolId") Long schoolId) {
        return new ResponseEntity<>(schoolService.getSchool(schoolId), HttpStatus.OK);
    }

    @PostMapping(path="/")
    public ResponseEntity<School> createSchool(@RequestBody School school){
        return new ResponseEntity<>(schoolService.createSchool(school), HttpStatus.OK);
    }

//    @PutMapping(path = "/{userId}")
//    public ResponseEntity<?> updateUser(
//            @RequestParam(required = false) String email,
//            @RequestParam(required = false) String password,
//            @PathVariable("userId") Long userId){
//        userService.updateUser(email, password, userId);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }

    @DeleteMapping(path = "/{schoolId}")
    public ResponseEntity<?> deleteSchool(@PathVariable("schoolId") Long schoolId){
        schoolService.deleteSchool(schoolId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
