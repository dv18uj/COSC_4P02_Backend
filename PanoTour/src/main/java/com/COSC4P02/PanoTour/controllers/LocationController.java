package com.COSC4P02.PanoTour.controllers;

import com.COSC4P02.PanoTour.entities.Artifact;
import com.COSC4P02.PanoTour.entities.Location;
import com.COSC4P02.PanoTour.entities.LocationDAO;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/location")
public class LocationController {

    @PostMapping
    @PreAuthorize("hasAuthority('museum:write')")
    @ResponseBody
    public int addLocation(@RequestBody Location location) {
        if (!LocationDAO.addLocation(location)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Location Could Not Be Added");
        }
        return location.getLid();
    }

    @DeleteMapping(path = "{lid}")
    @PreAuthorize("hasAuthority('museum:write')")
    public void deleteLocation(@PathVariable("lid") int lid) {
        Optional<Location> optionalLocation = LocationDAO.getLocationByLid(lid);
        if (optionalLocation.isPresent()) {
            Location location = optionalLocation.get();
            if (!LocationDAO.deleteLocation(location)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Location Could Not Be Deleted");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location Not Found");
        }
    }

    @GetMapping(path = "all")
    public List<Location> getAllLocations() {
        return LocationDAO.getAllLocations();
    }
}
