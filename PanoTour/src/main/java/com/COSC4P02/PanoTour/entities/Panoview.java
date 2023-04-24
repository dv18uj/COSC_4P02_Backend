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
    private int sid;

    @Column(name = "pid", nullable = false)
    private int pid;

    @Column(name = "pname", nullable = false)
    private String name;

    @Column(name = "image", nullable = false)
    private String image;

    public int getSid() { return sid; }
    public void setSid(int sid) { this.sid = sid; }
    public int getPid() { return pid; }
    public void setPid(int pid) { this.pid = pid;}
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
}