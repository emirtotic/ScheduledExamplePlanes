package com.scheduled.service;

import com.scheduled.model.Plane;

import java.util.List;

public interface PlaneService {

    List<Plane> findAll();
    Plane createPlane(Plane plane);
    void deletePlane(int planeId);

    Plane findPlaneById(int id);

    List<Plane> findAllPlanesByCompany(String companyName);

}
