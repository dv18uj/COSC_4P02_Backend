package com.COSC4P02.PanoTour.controllers;

import com.COSC4P02.PanoTour.entities.User;
import com.COSC4P02.PanoTour.entities.UserDAO;
import com.COSC4P02.PanoTour.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController
{
    private UserDAO userDAO;

    @Autowired
    public void setUser(@Qualifier("Users") UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @DeleteMapping(path = "{uid}")
    @PreAuthorize("hasAuthority('users:write')")
    public void deleteUser(@PathVariable("uid") int uid) {
        Optional<User> optionalArtifact = userDAO.getUserByUid(uid);
        if (optionalArtifact.isPresent()) {
            User artifact = optionalArtifact.get();
            if (!userDAO.deleteUser(artifact)) {
                throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User Could Not Be Deleted");
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Not Found");
        }
    }

    @PostMapping(path = "add")
    @PreAuthorize("hasAuthority('users:write')")
    public void addUser(@RequestBody User user) {
        if (!userDAO.addUser(user)) {
            if (user.getName() == null || user.getPassword() == null ||  user.getRole() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Properties Missing");
            }
            if (userDAO.getUserByName(user.getName()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Already Exists");
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User Could Not Be Added");
        }
    }

    @GetMapping(path = "get")
    @PreAuthorize("hasAuthority('users:read')")
    public Optional<User> getUsers(@RequestParam(value = "uid") int uid) {
        return userDAO.getUserByUid(uid);
    }
}
