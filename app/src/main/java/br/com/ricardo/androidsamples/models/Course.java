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
}
