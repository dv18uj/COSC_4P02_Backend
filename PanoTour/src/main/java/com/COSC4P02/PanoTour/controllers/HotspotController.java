package com.COSC4P02.PanoTour.controllers;


import com.COSC4P02.PanoTour.entities.Hotspot;
import com.COSC4P02.PanoTour.entities.HotspotDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/hotspot")
public class HotspotController {
    private HotspotDAO hotspotDAO;

    @Autowired
    public void setHotspot(@Qualifier("Hotspots") HotspotDAO hotspotDAO) {
        this.hotspotDAO = hotspotDAO;
    }


    @PostMapping
    @ResponseBody
    public int addHotspot(@RequestBody Hotspot hotspot) {
        if (!hotspotDAO.addHotspot(hotspot)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Hotspot Could Not Be Added");
        }
        return hotspot.getHid();
    }

    @GetMapping
    public List<Hotspot> getHotspotsByPid (@RequestParam(value = "pid") int pid) {
        return HotspotDAO.getHotspotsByPid(pid);
    }
}