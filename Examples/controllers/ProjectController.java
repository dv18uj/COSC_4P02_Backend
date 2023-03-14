package com.project.TalonMillwork.controllers;

import com.project.TalonMillwork.entities.Project;
import com.project.TalonMillwork.entities.ProjectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/project")
public class ProjectController {
    private ProjectDao projectDao;

    @Autowired
    public void setProjectDao(@Qualifier("Projects") ProjectDao projectDao) {
        this.projectDao = projectDao;
    }

    @GetMapping
    public List<Project> getAllProjects() {
        return projectDao.getAllProjects();
    }

    @PostMapping
    public void addProject(@RequestBody Project project) {
        if (!projectDao.addProject(project)) {
            if (project.getPname() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Project Properties Missing");
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Project Could Not Be Added");
        }
    }

    @DeleteMapping(path = "{pid}")
    public void deleteProject(@PathVariable("pid") int pid) {
        Optional<Project> optionalProject = projectDao.getProjectByPid(pid);
        if (optionalProject.isPresent()) {
           Project project = optionalProject.get();
           if (!projectDao.deleteProject(project)) {
               throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Project Could Not Be Deleted");
           }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Project Not Found");
        }

    }

    @GetMapping(path = "{pid}")
    public Project getProject(@PathVariable("pid") int pid) {
        Optional<Project> optionalProject = projectDao.getProjectByPid(pid);
        if (optionalProject.isPresent()) {
            return optionalProject.get();
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Project Not Found");
        }
    }
}
