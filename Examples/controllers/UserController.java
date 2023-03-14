package com.project.TalonMillwork.controllers;

import com.project.TalonMillwork.auth.ApplicationUser;
import com.project.TalonMillwork.entities.User;
import com.project.TalonMillwork.entities.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {
    private UserDao userDao;

    @Autowired
    public void setUserDao(@Qualifier("Users") UserDao userDao) {
        this.userDao = userDao;
    }

    @GetMapping
    public User getApplicationUser(@AuthenticationPrincipal ApplicationUser applicationUser) {
        Optional<User> user = userDao.getUserByUname(applicationUser.getUsername());
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Application Details Not Found");
        }
    }

    @GetMapping(path = "{uname}")
    public User getUser(@PathVariable("uname") String uname) {
        Optional<User> user = userDao.getUserByUname(uname);
        if (user.isPresent()) {
            return user.get();
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Username Provided Not Found");
        }
    }

    @GetMapping(path = "all")
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addUser(@RequestBody User user) {
        if (!user.getActive()) user.setActive(true);

        if (!userDao.addUser(user)) {
            if (user.getlName() == null || user.getfName() == null
                    || user.getPassword() == null || user.getUname() == null || user.getRole() == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Properties Missing");
            }
            if (userDao.getUserByUname(user.getUname()).isPresent()) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Already Exists");
            }
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User Could Not Be Added");
        }
    }

    @DeleteMapping(path = "{uname}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUser(@PathVariable("uname") String uname) {
        User user = userDao.getUserByUname(uname)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Username Provided Not Found"));
        if (!userDao.deleteUser(user)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User Could Not Be Deleted");
        }
    }

    @PutMapping()
    public void updateUser(@AuthenticationPrincipal ApplicationUser applicationUser, @RequestBody String[] strings) {
        String newFname = strings[0];
        String newLname = strings[1];
        String newPassword = strings[2];
        String newQuestion = strings[3];
        String newAnswer = strings[4];
        User user = userDao.getUserByUname(applicationUser.getUsername())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Username Provided Not Found"));

        if (newFname != null) {
            user.setfName(newFname);
        }
        if (newLname != null) {
            user.setlName(newLname);
        }
        if (newPassword != null) {
            user.setPassword(newPassword);
        }
        if (newPassword != null) {
            user.setRecoveryQuestion(newQuestion);
        }
        if (newPassword != null) {
            user.setRecoveryAnswer(newAnswer);
        }
        if (!userDao.updateUser(user)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User Could Not Be Updated");
        }
    }

    @PutMapping(path = "{uname}/permissions")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void updateUserPermissions(@PathVariable("uname") String uname,
                                      @RequestParam(name = "role",required = false) String newRole,
                                      @RequestParam(name = "active",required = false) Boolean newActive,
                                      @RequestParam(name = "password",required = false) String newPassword) {
        User user = userDao.getUserByUname(uname)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Username Provided Not Found"));
        if (newRole != null) {
            user.setRole(newRole);
        }
        if (newActive != null) {
            user.setActive(newActive);
        }
        if (newPassword != null) {
            user.setPassword(newPassword);
        }
        if (!userDao.updateUser(user)) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "User Could Not Be Updated");
        }
    }

    @GetMapping(path = "forgotPassword")
    public String getRecoveryQuestion(@RequestParam("uname") String uname) {
        User user = userDao.getUserByUname(uname)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Username Provided Not Found"));
        return user.getRecoveryQuestion();
    }

    @PutMapping(path = "forgotPassword")
    public void getResetPassword(@RequestBody String[] strings) {
        String uname = strings[0];
        String answer = strings[1];
        String newPassword = strings[2];
        User user = userDao.getUserByUname(uname)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Username Provided Not Found"));
        String recAns = user.getRecoveryAnswer();

        if (recAns != null && BCrypt.checkpw(answer, recAns)) {
            if (newPassword != null) {
                user.setPassword(newPassword);
                if (!userDao.updateUser(user)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User Could Not Be Updated");
                }
            }
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Incorrect Recovery Answer");
        }
    }
}
