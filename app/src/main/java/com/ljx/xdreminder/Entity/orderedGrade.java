package com.ljx.xdreminder.Entity;

import java.util.List;

public class orderedGrade implements Comparable<orderedGrade>{
    private String semester;
    private List<String> lessons;
    private int order;

    public orderedGrade(String semester, List<String> lessons, int order) {
        this.semester = semester;
        this.lessons = lessons;
        this.order = order;
    }

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

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int compareTo(orderedGrade o) {
        return order - o.order;
    }

}
