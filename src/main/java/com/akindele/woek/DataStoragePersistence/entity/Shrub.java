package com.akindele.woek.DataStoragePersistence.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
public class Shrub extends Plant {

    private Integer height;
    private Integer width;

    public Shrub(Integer height, Integer width) {
        this.height = height;
        this.width = width;
    }

    public Shrub(){}

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }
}
