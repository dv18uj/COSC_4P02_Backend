package com.COSC4P02.PanoTour.auth;

import com.COSC4P02.PanoTour.entities.UserDAO;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.COSC4P02.PanoTour.security.ApplicationUserRole.EMPLOYEE;
import static com.COSC4P02.PanoTour.security.ApplicationUserRole.OWNER;

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
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        EMPLOYEE.getGrantedAuthorities(),
                        passwordEncoder.encode("password"),
                        "employee",
                        true,
                        true,
                        true,
                        true),
                new ApplicationUser(
                        OWNER.getGrantedAuthorities(),
                        passwordEncoder.encode("root"),
                        "root",
                        true,
                        true,
                        true,
                        true)
        );
        return applicationUsers;
    }
}