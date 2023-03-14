package com.project.TalonMillwork.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "project")
public class Project implements Serializable {
    private static final long serialVersionUID = 223L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pid;

    @Column(name = "pname", nullable = false)
    private String pname;

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPid() {
        return pid;
    }
}
