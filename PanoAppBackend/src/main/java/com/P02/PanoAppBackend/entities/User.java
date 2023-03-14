package com.P02.PanoAppBackend.entities;

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
    private String url;

    public int getUid() { return uid; }
    public String getName() { return name; }
    public String getUrl() { return url; }
}