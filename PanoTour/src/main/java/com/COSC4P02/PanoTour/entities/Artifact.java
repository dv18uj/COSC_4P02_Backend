package com.COSC4P02.PanoTour.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * TODO: These are the elements which need to be related with the Artifact
 */

@Entity
@Table(name = "artifact")
public class Artifact implements Serializable {
    private static final long serialVersionUID = 338L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int oid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "url")
    private String url;

    @Column(name = "date")
    private Date date;

    @Column(name = "description")
    private String description;

    @Column(name = "people")
    private String people;

    @Column(name = "subject")
    private String subjects;

    @Column(name = "dimensions")
    private String dimensions;

    @Column(name = "title")
    private String title;

    @Column(name = "photographer")
    private String photographer;

    @Column(name = "studio")
    private String studio;

    @Column(name = "numberOfImages", nullable = false)
    private int numberOfImages;

    public int getOid() { return oid; }

    public String getUrl() { return url; }

    public String getName() { return name; }

    public Date getDate() { return date; }

    public String getDesc() { return description; }

    public String getPeople() { return people; }

    public String getDimensions() { return dimensions; }

    public String getTitle() { return title; }

    public String getPhotographer() { return photographer; }

    public String getStudio() { return studio; }

    public int getNumberOfImages() {
        return numberOfImages;
    }
}
