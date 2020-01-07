package com.example.learning.beans;

import org.litepal.crud.DataSupport;

import java.io.Serializable;

public class ClassBean extends DataSupport implements Serializable {

    private String name;
    private int pic;
    private int id;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
