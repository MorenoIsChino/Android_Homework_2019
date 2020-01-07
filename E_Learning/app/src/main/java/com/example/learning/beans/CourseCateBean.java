package com.example.learning.beans;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;




public class CourseCateBean extends DataSupport implements Serializable {

    private int id;
    private String title;
    private List<CourseItem> list;


    public int getId() {
        return id;
    }

    public CourseCateBean setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public CourseCateBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<CourseItem> getList() {
        return list;
    }

    public CourseCateBean setList(List<CourseItem> list) {
        this.list = list;
        return this;
    }

}
