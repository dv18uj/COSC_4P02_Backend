package com.project.TalonMillwork.entities;

import javax.persistence.*;
import java.io.Serializable;


public class ReportImage implements Serializable {
    private static final long serialVersionUID = 762L;

    private int imgId;

    //The foreign key of the report
    private int rid;

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
