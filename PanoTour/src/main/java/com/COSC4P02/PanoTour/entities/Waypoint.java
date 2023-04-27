package com.COSC4P02.PanoTour.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "waypoint")
public class Waypoint implements Serializable
{
    private static final long serialVersionUID = 338L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int wid;

    //The foreign key of the project
    @Column(name = "pid", nullable = false)
    private int pid;

    @Column(name = "toPid", nullable = false)
    private int toPid;

    @Column(name = "px", nullable = false)
    private int px;

    @Column(name = "py", nullable = false)
    private int py;

    @Column(name = "pz", nullable = false)
    private int pz;

    @Column(name = "rx", nullable = false)
    private int rx;

    @Column(name = "ry", nullable = false)
    private int ry;

    @Column(name = "rz", nullable = false)
    private int rz;

    public int getWid() { return wid; }
    public int getPid() { return pid; }
    public int getToPid() { return toPid;}
    public int getPx() { return px; }
    public int getPy() { return py; }
    public int getPz() { return pz; }
    public int getRx() { return rx; }
    public int getRy() { return ry; }
    public int getRz() { return rz; }
}