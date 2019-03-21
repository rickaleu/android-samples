package br.com.ricardo.androidsamples.models;

import java.util.List;

public class Course {

    public String title;
    public String subtitle;
    public List<Instructor> instructors;

    public Course(String title, String subtitle, List<Instructor> instructors) {
        this.title = title;
        this.subtitle = subtitle;
        this.instructors = instructors;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<Instructor> getInstructors() {
        return instructors;
    }

    public void setInstructors(List<Instructor> instructors) {
        this.instructors = instructors;
    }
}
