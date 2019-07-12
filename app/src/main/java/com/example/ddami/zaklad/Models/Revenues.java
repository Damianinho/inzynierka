package com.example.ddami.zaklad.Models;

import java.sql.Date;

/**
 * Created by ddami on 1/10/2018.
 */

public class Revenues {
    int id;
    double cost;
    String przychody;



    public Revenues(){

    }
    public Revenues(double cost, String przychody) {
        this.cost = cost;
        this.przychody = przychody;

    }

    public Revenues(int id, double cost,String przychody) {
        this.id = id;
        this.cost = cost;
        this.przychody = przychody;

    }

    public void setId(int id) {
        this.id = id;
    }
    public long getId() {
        return this.id;
    }
    public void setCost(float cost) {
        this.cost = cost;
    }
    public double getCost() {
        return this.cost;
    }
    public void setPrzychody(String przychody) {
        this.przychody = przychody;
    }
    public String getPrzychody() {
        return this.przychody;
    }

}
