package com.odezsa.konik.neobaba;

import android.widget.ImageView;

/**
 * Created by konik on 5/29/2016.
 */
public abstract class ReceptSample {
    private Integer img;
    private String name;
    private String description;
    private int id;

    public ReceptSample(String name) {
        this.name = name;

    }

    public Integer getImg() {
        return img;
    }

    public void setImg(Integer img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
