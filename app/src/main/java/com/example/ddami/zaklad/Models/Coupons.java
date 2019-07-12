package com.example.ddami.zaklad.Models;

/**
 * Created by ddami on 30.11.2017.
 */
public class Coupons {
    int id;
    String home, away;
    float winn,course;

    public Coupons(){

    }
    public Coupons(String home, String away, float winn, float course) {
        this.home = home;
        this.away = away;
        this.winn = winn;
        this.course = course;

    }

    public Coupons(int id,String home, String away, float winn, float course) {
        this.id = id;
        this.home = home;
        this.away = away;
        this.winn = winn;
        this.course = course;
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
    public void setCourse(float course){
        this.course = course;
    }
    public float getCourse(){
        return this.course;
    }
    public void setWinn(float winn){
        this.winn = winn;
    }
    public float getWinn(){
        return this.winn;
    }

}
