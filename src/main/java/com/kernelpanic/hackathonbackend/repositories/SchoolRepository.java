package com.kernelpanic.hackathonbackend.repositories;

import com.kernelpanic.hackathonbackend.entities.school.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Long> {
    boolean existsByEmail(String email);

}
