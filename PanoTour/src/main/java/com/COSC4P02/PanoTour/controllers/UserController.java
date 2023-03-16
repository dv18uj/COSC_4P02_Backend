package com.COSC4P02.PanoTour.controllers;

import com.COSC4P02.PanoTour.entities.User;
import com.COSC4P02.PanoTour.entities.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    @ResponseBody
    public int addUser(@RequestBody User user) {
        if (!userDAO.addUser(user)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User Could Not Be Added");
        }
        return user.getUid();
    }

    @GetMapping(path = "get")
    public Optional<User> getUsers(@RequestParam(value = "uid") int uid) {
        return userDAO.getUserByUid(uid);
    }
}
