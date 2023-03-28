package com.COSC4P02.PanoTour.controllers;

import com.COSC4P02.PanoTour.entities.Waypoint;
import com.COSC4P02.PanoTour.entities.WaypointDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/waypoint")
public class WaypointController
{
    private WaypointDAO waypointDAO;

    @Autowired
    public void setWaypoint(@Qualifier("Waypoints") WaypointDAO waypointDAO) {
        this.waypointDAO = waypointDAO;
    }

    @DeleteMapping(path = "{pid}")
    public void deleteUser(@PathVariable("pid") int pid) {
        Optional<Waypoint> optionalArtifact = waypointDAO.getWaypointByPid(pid);
        if (optionalArtifact.isPresent()) {
            Waypoint artifact = optionalArtifact.get();
            if (!waypointDAO.deleteWaypoint(artifact)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User Could Not Be Deleted");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Not Found");
        }
    }

    @PostMapping
    @ResponseBody
    public int addWaypoint(@RequestBody Waypoint waypoint) {
        if (!waypointDAO.addWaypoint(waypoint)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Waypoint Could Not Be Added");
        }
        return waypoint.getPid();
    }

    @GetMapping(path = "get")
    public Optional<Waypoint> getWaypoints(@RequestParam(value = "pid") int pid) {
        return waypointDAO.getWaypointByPid(pid);
    }
}