package com.project.TalonMillwork.entities;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "report_postimage")
public class Report_postImage extends ReportImage {
    private static final long serialVersionUID = 762L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int imgId;

    //The foreign key of the report
    @Column(name = "rid", nullable = false)
    private int rid;

    @Lob
    @Column(name = "image", nullable = false)
    private byte[] image;

    public void setRid(int rid) {
        this.rid = rid;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public int getRid() {
        return rid;
    }

    public int getImgId() {
        return imgId;
    }

    public byte[] getImage() {
        return image;
    }
}
