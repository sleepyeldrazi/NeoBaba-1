package com.odezsa.konik.neobaba;

import android.widget.ImageView;

/**
 * Created by Konik on 5/9/16.
 */
public class Food {
    ImageView img;
    boolean isChecked = false;
    String name;
    Food(String ime, ImageView image){
        this.name=ime;
        this.img = image;
    }

    public ImageView getImg() {
        return img;
    }

    public void setImg(ImageView img) {
        this.img = img;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
