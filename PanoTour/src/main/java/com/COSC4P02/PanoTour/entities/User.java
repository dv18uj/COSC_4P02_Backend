package com.COSC4P02.PanoTour.entities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "`user`") // "user" is a keyword in hibernate, must use grave accents or else tests will not run properly
public class User implements Serializable
{
    private static final long serialVersionUID = 338L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int uid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "role")
    private String role;

    public int getUid() { return uid; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = new BCryptPasswordEncoder(10).encode(password); }
    public String getRole() { return role; }
    public void setRole(String roles) { this.role = roles; }
}