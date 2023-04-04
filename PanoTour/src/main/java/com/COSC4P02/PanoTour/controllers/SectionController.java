package com.COSC4P02.PanoTour.controllers;


import com.COSC4P02.PanoTour.entities.Section;
import com.COSC4P02.PanoTour.entities.SectionDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/section")
public class SectionController {
    private SectionDAO sectionDAO;

    @Autowired
    public void setSection(@Qualifier("SECTION") SectionDAO sectionDAO) {
        this.sectionDAO = sectionDAO;
    }


    @PostMapping
    @PreAuthorize("hasAuthority('museum:write')")
    @ResponseBody
    public int addSection(@RequestBody Section section) {
        if (!sectionDAO.addSection(section)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Artifact Could Not Be Added");
        }
        return section.getSid();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('users:read')")
    public Optional<Section> getSection(@RequestParam(value = "sid") int sid) {
        return sectionDAO.getSectionBySid(sid);
    }

}