package com.COSC4P02.PanoTour.controllers;

import com.COSC4P02.PanoTour.entities.Hotspot;
import com.COSC4P02.PanoTour.entities.HotspotDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/hotspot")
public class HotspotController
{
    private HotspotDAO hotspotDAO;

    @Autowired
    public void setHotspot(@Qualifier("Hotspots") HotspotDAO hotspotDAO) {
        this.hotspotDAO = hotspotDAO;
    }

    @DeleteMapping(path = "{oid}")
    public void deleteHotspot(@PathVariable("oid") int oid) {
        Optional<Hotspot> optionalArtifact = hotspotDAO.getHotspotByOid(oid);
        if (optionalArtifact.isPresent()) {
            Hotspot artifact = optionalArtifact.get();
            if (!hotspotDAO.deleteHotspot(artifact)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Hotspot Could Not Be Deleted");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Hotspot Not Found");
        }
    }

    @PostMapping
    @ResponseBody
    public int addHotspot(@RequestBody Hotspot hotspot) {
        if (!hotspotDAO.addHotspot(hotspot)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Hotspot Could Not Be Added");
        }
        return hotspot.getOid();
    }

    @GetMapping(path = "get")
    public Optional<Hotspot> getHotspots(@RequestParam(value = "oid") int oid) {
        return hotspotDAO.getHotspotByOid(oid);
    }
}