package com.scheduled.controller;

import com.scheduled.model.Plane;
import com.scheduled.service.PlaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/plane")
@RequiredArgsConstructor
public class PlaneController {

    private final PlaneService planeService;

    @GetMapping("/all")
    public ResponseEntity<List<Plane>> getAllPlanes() {
        return new ResponseEntity<>(planeService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/create")
    public ResponseEntity<Plane> createPlane(@RequestBody Plane plane) {
        return new ResponseEntity<>(planeService.createPlane(plane), HttpStatus.CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePlane(@PathVariable(name = "id") int id) {
        planeService.deletePlane(id);
        return new ResponseEntity<>("Plane deleted successfully!", HttpStatus.OK);
    }

}
