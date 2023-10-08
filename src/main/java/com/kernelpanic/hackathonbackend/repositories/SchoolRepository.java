package com.kernelpanic.hackathonbackend.repositories;

import com.kernelpanic.hackathonbackend.entities.school.School;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SchoolRepository extends JpaRepository<School, Long> {
    boolean existsByEmail(String email);
    @Query(value = "SELECT * FROM schools s WHERE ST_DWithin(ST_MakePoint(s.latitude, s.longitude), ST_MakePoint(?1, ?2), ?3)  ORDER BY ST_Distance(ST_MakePoint(s.latitude, s.longitude), ST_MakePoint(?1, ?2))", nativeQuery = true)
    List<School> findSchoolsWithinDistance(double latitude, double longitude, double distance);

}
