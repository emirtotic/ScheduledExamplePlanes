package com.scheduled.service.impl;

import com.scheduled.exception.PlaneCreationException;
import com.scheduled.exception.PlaneNotFoundException;
import com.scheduled.model.Plane;
import com.scheduled.repository.PlaneRepository;
import com.scheduled.service.PlaneService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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
        Plane plane = planeRepository.findById(planeId)
                .orElseThrow(() -> new PlaneNotFoundException(planeId));

        planeRepository.deleteById(planeId);
    }
}
