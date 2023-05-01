package com.COSC4P02.PanoTour.controllers;


import com.COSC4P02.PanoTour.entities.Waypoint;
import com.COSC4P02.PanoTour.entities.WaypointDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/waypoint")
public class WaypointController {
    private WaypointDAO waypointDAO;

    @Autowired
    public void setWaypoint(@Qualifier("Waypoints") WaypointDAO waypointDAO) {
        this.waypointDAO = waypointDAO;
    }


    @PostMapping
    @PreAuthorize("hasAuthority('museum:write')")
    @ResponseBody
    public int addWaypoint(@RequestBody Waypoint waypoint) {
        if (!waypointDAO.addWaypoint(waypoint)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Waypoint Could Not Be Added");
        }
        return waypoint.getWid();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('museum:read')")
    public List<Waypoint> getWaypointsByPid (@RequestParam(value = "pid") int pid) {
        return WaypointDAO.getWaypointsByPid(pid);
    }
}