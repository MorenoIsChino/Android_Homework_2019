package com.example.learning.beans;

import org.litepal.crud.DataSupport;

import java.io.Serializable;
import java.util.List;




public class MyCourseBean extends DataSupport implements Serializable {

    private int id;
    private String title;
    private List<CourseItem> list;


    public int getId() {
        return id;
    }

    public MyCourseBean setId(int id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MyCourseBean setTitle(String title) {
        this.title = title;
        return this;
    }

    public List<CourseItem> getList() {
        return list;
    }

    public MyCourseBean setList(List<CourseItem> list) {
        this.list = list;
        return this;
    }

}
