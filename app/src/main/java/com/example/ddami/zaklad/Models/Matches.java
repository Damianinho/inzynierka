package com.example.ddami.zaklad.Models;

/**
 * Created by ddami on 26.11.2017.
 */

public class Matches {
    int id;
    String home, away;
    float course_home,course_away,course_draw;

    public Matches(){

    }
    public Matches(String home, String away, float course_home, float course_away, float course_draw) {
        this.home = home;
        this.away = away;
        this.course_home = course_home;
        this.course_away = course_away;
        this.course_draw = course_draw;
    }

    public Matches(int id, String home, String away, float course_home, float course_away, float course_draw) {
        this.id = id;
        this.home = home;
        this.away = away;
        this.course_home = course_home;
        this.course_away = course_away;
        this.course_draw = course_draw;
    }

    public void setId(int id) {
        this.id = id;
    }
    public long getId() {
        return this.id;
    }
    public void setHome(String home){
        this.home = home;
    }
    public String getHome(){
        return this.home;
    }
    public void setAway(String away){
        this.away = away;
    }
    public String getAway(){
        return this.away;
    }
    public void setCourse_home(float course_home){
        this.course_home = course_home;
    }
    public float getCourse_home(){
        return this.course_home;
    }
    public void setCourse_away(float course_away){
        this.course_away = course_away;
    }
    public float getCourse_away(){
        return this.course_away;
    }
    public void setCourse_draw(float course_draw){
        this.course_draw = course_draw;
    }
    public float getCourse_draw(){
        return this.course_draw;
    }
}
