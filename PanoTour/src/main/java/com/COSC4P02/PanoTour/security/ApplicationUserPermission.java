package com.COSC4P02.PanoTour.security;

public enum ApplicationUserPermission {
    MUSEUM_READ("museum:read"),
    MUSEUM_WRITE("museum:write"),
    USERS_READ("users:read"),
    USERS_WRITE("users:write");

    private final String permission;

    ApplicationUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;
    }
}
