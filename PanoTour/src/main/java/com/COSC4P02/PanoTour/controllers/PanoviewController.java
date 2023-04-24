package com.COSC4P02.PanoTour.controllers;


import com.COSC4P02.PanoTour.entities.Panoview;
import com.COSC4P02.PanoTour.entities.PanoviewDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/panoview")
public class PanoviewController {
    private PanoviewDAO panoviewDAO;

    @Autowired
    public void setPanoview(@Qualifier("Panoview") PanoviewDAO panoviewDAO) {
        this.panoviewDAO = panoviewDAO;
    }


    @PostMapping
    @PreAuthorize("hasAuthority('museum:write')")
    @ResponseBody
    public int addPanoview(@RequestBody Panoview panoview) {
        if (!panoviewDAO.addPanoview(panoview)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "panoview Could Not Be Added");
        }
        return panoview.getSid();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('users:read')")
    public Optional<Panoview> getPanoview(@RequestParam(value = "sid") int sid) {
        return panoviewDAO.getPanoviewBySid(sid);
    }

}