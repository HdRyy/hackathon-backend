package com.kernelpanic.hackathonbackend.DTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Location {
    private String type;
    private Coordinates coordinates;

    public Location(String type, Coordinates coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }
// Constructors, getters, and setters

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }
}
