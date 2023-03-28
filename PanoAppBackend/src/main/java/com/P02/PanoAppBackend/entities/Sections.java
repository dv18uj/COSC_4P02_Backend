package com.P02.PanoAppBackend.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "section")
public class Sections implements Serializable
{
    private static final long serialVersionUID = 338L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int sid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "lid")
    private int lid;

    public int getUid() { return sid; }
    public int getLid() { return lid; }
    public String getName() { return name; }
    public String getDescription() { return description; }
}