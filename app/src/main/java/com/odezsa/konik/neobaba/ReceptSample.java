package com.odezsa.konik.neobaba;

import android.widget.ImageView;

/**
 * Created by konik on 5/29/2016.
 */
public abstract class ReceptSample {
    private ImageView img;
    private String name;
    private String[] products;
    private String description;

    public ReceptSample(ImageView img, String name, String[] products, String description) {
        this.img = img;
        this.name = name;
        this.products = products;
        this.description = description;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setProducts(String[] products) {
        this.products = products;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ImageView getImg() {
        return img;
    }

    public String getName() {
        return name;
    }

    public String[] getProducts() {
        return products;
    }

    public String getDescription() {
        return description;
    }
}
