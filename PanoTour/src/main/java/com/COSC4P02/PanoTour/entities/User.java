package com.COSC4P02.PanoTour.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user")
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

    public int getUid() { return uid; }
    public String getName() { return name; }
    public String getPassword() { return password; }
}