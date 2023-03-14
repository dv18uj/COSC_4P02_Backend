package com.project.TalonMillwork.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * TODO: These are the elements which need to be related with the report
 * preImages --- images relational table
 * postImages --- images relational table
 */

@Entity
@Table(name = "report")
public class Report implements Serializable {
    private static final long serialVersionUID = 338L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rid;

    @Column(name = "rname", nullable = false)
    private String rname;

    //The foreign key of the project
    @Column(name = "pid", nullable = false)
    private int pid;

    @Column(name = "date", nullable = false)
    private Date date;

    //Humidity is a percent
    @Column(name = "humidity")
    private double humidity;

    @Column(name = "weather")
    private String weather;

    @Column(name = "siteConditions")
    private String siteConditions;

    @Column(name = "toDo")
    private String toDo;

    @Column(name = "obstacles")
    private String obstacles;

    @Column(name = "notes")
    private String notes;

    @Column(name = "nextDayPlan")
    private String nextDayPlan;

    @Column(name = "creatorUid", nullable = false)
    private int creatorUid;

    @Column(name = "supervisorUid")
    private int supervisorUid;

    public int getRid() { return rid; }

    public String getRName() { return rname; }

    public int getPid() { return pid; }

    public Date getDate() { return date; }

    public double getHumidity() { return humidity; }

    public String getWeather() { return weather; }

    public String getSiteConditions() { return siteConditions; }

    public String getToDo() { return toDo; }

    public String getObstacles() { return obstacles; }

    public String getNotes() { return notes; }

    public String getNextDayPlan() { return nextDayPlan; }

    public int getCreatorUid() {
        return creatorUid;
    }

    public int getSupervisorUid() {
        return supervisorUid;
    }
}
