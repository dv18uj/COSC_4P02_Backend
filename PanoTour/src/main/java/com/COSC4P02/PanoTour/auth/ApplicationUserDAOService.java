package com.COSC4P02.PanoTour.auth;

import com.COSC4P02.PanoTour.entities.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("ApplicationUsers")
public class ApplicationUserDAOService implements ApplicationUserDAO {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    private UserDAO userDao;


    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = null;
        return applicationUsers;
    }
}