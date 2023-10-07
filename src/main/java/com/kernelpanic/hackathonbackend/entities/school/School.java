package com.kernelpanic.hackathonbackend.entities.school;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "schools")
@Table
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private String cep;
    @Column(nullable = false)
    private double latitude;
    @Column(nullable = false)
    private double longitude;

    public School(String email, String name, String cep) {
        this.email = email;
        this.name = name;
        this.cep = cep;
    }
}
