package com.P02.PanoTour.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "waypoint")
public class Waypoint implements Serializable
{
    private static final long serialVersionUID = 338L;


    //The foreign key of the project
    @Column(name = "sid", nullable = false)
    private int sid;

    //The foreign key of the project
    @Column(name = "pid", nullable = false)
    private int pid;

    @Column(name = "x", nullable = false)
    private int x;

    @Column(name = "y", nullable = false)
    private int y;

    @Column(name= "z", nullable = false)
    private int z;

    public int getPid{return pid;}
    public int getSid() {return sid;}
    public int getX() { return x; }
    public int getY() { return y; }
    public int getZ(){return z;}
}