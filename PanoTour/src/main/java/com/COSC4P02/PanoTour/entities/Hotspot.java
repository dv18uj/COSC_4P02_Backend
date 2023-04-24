package com.COSC4P02.PanoTour.entities;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "hotspot")
public class Hotspot implements Serializable
{
    private static final long serialVersionUID = 338L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hid;

    //The foreign key of the project
    @Column(name = "pid", nullable = false)
    private int pid;

    //The foreign key of the project
    @Column(name = "oid", nullable = false)
    private int oid;

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

    public int getHid() { return hid; }
    public int getOid() { return oid; }
    public int getPid() { return pid; }
    public int getPx() { return px; }
    public int getPy() { return py; }
    public int getPz() { return pz; }
    public int getRx() { return rx; }
    public int getRy() { return ry; }
    public int getRz() { return rz; }
}