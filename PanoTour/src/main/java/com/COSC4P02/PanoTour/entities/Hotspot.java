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
    @Column(name = "sid", nullable = false)
    private int sid;

    //The foreign key of the project
    @Column(name = "oid", nullable = false)
    private int oid;

    @Column(name = "x", nullable = false)
    private int x;

    @Column(name = "y", nullable = false)
    private int y;

    @Column(name = "z", nullable = false)
    private int z;

    public int getHid() { return hid; }
    public int getOid() { return oid; }
    public int getSid() { return sid; }
    public int getX() { return x; }
    public int getY() { return y; }
    public int getZ() { return z; }
}