package com.COSC4P02.PanoTour.controllers;

import com.COSC4P02.PanoTour.entities.Artifact;
import com.COSC4P02.PanoTour.entities.Location;
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
    public int addArtifact(@RequestBody Artifact artifact) {
        if (!artifactDAO.addArtifact(artifact)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Artifact Could Not Be Added");
        }
        return artifact.getOid();
    }

    @DeleteMapping(path = "{oid}")
    @PreAuthorize("hasAuthority('museum:write')")
    public void deleteArtifact(@PathVariable("oid") int oid) {
        Optional<Artifact> optionalArtifact = artifactDAO.getArtifactByOid(oid);
        if (optionalArtifact.isPresent()) {
            Artifact artifact = optionalArtifact.get();
            if (!artifactDAO.deleteArtifact(artifact)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Report Could Not Be Deleted");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Report Not Found");
        }
    }

    @GetMapping(path = "all")
    public List<Location> getAllUsers() {
        return locationDao.getAllUsers();
    }
}
