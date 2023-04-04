package com.COSC4P02.PanoTour.auth;

import java.util.Optional;

public interface ApplicationUserDAO
{
    Optional<ApplicationUser> selectApplicationUserByUsername(String username);
}
