package com.ljx.xdreminder.Entity;

import java.util.List;

public class grades {
    private String semester;
    private List<String> lessons;

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    public List<String> getLessons() {
        return lessons;
    }

    public void setLessons(List<String> lessons) {
        this.lessons = lessons;
    }
}
