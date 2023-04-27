package com.COSC4P02.PanoTour.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "panoview")
public class Panoview implements Serializable
{
    private static final long serialVersionUID = 338L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    //The foreign key of the project
    @Column(name = "sid", nullable = false)
    private int sid;

    @Column(name = "image", nullable = false)
    private String image;

    public int getPid() { return pid; }
    public int getSid() { return sid; }
    public String getImage() { return image; }
}