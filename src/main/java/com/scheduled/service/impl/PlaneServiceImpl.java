package com.scheduled.service.impl;

import com.scheduled.exception.PlaneCreationException;
import com.scheduled.exception.PlaneNotFoundException;
import com.scheduled.model.Plane;
import com.scheduled.repository.PlaneRepository;
import com.scheduled.service.PlaneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class PlaneServiceImpl implements PlaneService {

    private final PlaneRepository planeRepository;

    @Override
    public List<Plane> findAll() {
        log.info("Finding all planes...");
        return planeRepository.findAll();
    }

    @Override
    public Plane createPlane(Plane plane) {

        if (plane.getBookedSpace() > plane.getSpace()) {
            throw new PlaneCreationException("Not enough space in the plane.");
        }
        log.info("Plane Booked: {}. Seats left: {}", plane.getPlaneName() + " - " + plane.getCompanyName(),
                plane.getSpace() - plane.getBookedSpace());
        return planeRepository.save(plane);
    }

    @Override
    public void deletePlane(int planeId) {
        planeRepository.findById(planeId)
                .orElseThrow(() -> new PlaneNotFoundException(planeId));
        log.info("Plane deleted successfully.");
        planeRepository.deleteById(planeId);
    }

    @Override
    public Plane findPlaneById(int id) {
        return planeRepository.findById(id).orElseThrow(() -> new PlaneNotFoundException(id));
    }

    @Override
    public List<Plane> findAllPlanesByCompany(String companyName) {

        List<Plane> planes = planeRepository.findAllByCompanyNameContainsIgnoreCase(companyName);

        if (planes.size() > 0) {
            log.info("Found {} planes of {} company.", planes.size(), companyName);
            return planes;
        }
        log.info("There are no {} planes!", companyName);
        return new ArrayList<>();
    }
}
