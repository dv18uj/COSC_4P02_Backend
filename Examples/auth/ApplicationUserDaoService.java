package com.project.TalonMillwork.auth;

import com.google.common.collect.Lists;
import com.project.TalonMillwork.entities.UserDao;
import com.project.TalonMillwork.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.project.TalonMillwork.security.ApplicationUserRole.*;

@Repository("ApplicationUsers")
public class ApplicationUserDaoService implements ApplicationUserDao {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
    private UserDao userDao;

    @Autowired
    public void setUserDao(@Qualifier("Users") UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers().stream()
                .filter(applicationUser -> username.equals(applicationUser.getUsername()))
                .findFirst();
    }

    /**
     * This provides a list of fake users and real users in the DB
     */
    //TODO: SECURITY RISK! REMOVE FAKE USERS
    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        EMPLOYEE.getGrantedAuthorities(),
                        passwordEncoder.encode("password"),
                        "johnsmith",
                        true,
                        true,
                        true,
                        true),
                new ApplicationUser(
                        ADMIN.getGrantedAuthorities(),
                        passwordEncoder.encode("root"),
                        "root",
                        true,
                        true,
                        true,
                        true)
        );
        userDao.getAllUsers().stream()
                .map(user -> new ApplicationUser(
                        ApplicationUserRole.valueOf(user.getRole()).getGrantedAuthorities(),
                        user.getPassword(),
                        user.getUname(),
                        user.getActive(),
                        user.getActive(),
                        user.getActive(),
                        user.getActive()))
                .forEach(applicationUsers::add);
        return applicationUsers;
    }
}
