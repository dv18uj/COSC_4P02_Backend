package com.COSC4P02.PanoTour.controllers;

import com.COSC4P02.PanoTour.entities.Artifact;
import com.COSC4P02.PanoTour.entities.Location;
import com.COSC4P02.PanoTour.entities.LocationDAO;
import com.COSC4P02.PanoTour.entities.SectionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/location")
public class LocationController
{
    private LocationDAO locationDAO;

    @Autowired
    public void setLocation(@Qualifier("Locations") LocationDAO locationDAO) {
        this.locationDAO = locationDAO;
    }

    @PostMapping
    @PreAuthorize("hasAuthority('museum:write')")
    @ResponseBody
    public int addLocation(@RequestBody Location location) {
        if (!locationDAO.addLocation(location)) {
            System.out.println("Throwing error.");
            if (location.getName() == null || location.getImage() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Location Properties Missing");
            }
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
