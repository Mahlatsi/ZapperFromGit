package com.example.monty.zapper.medels;

import java.io.Serializable;

/**
 * this is like a pojo class
 * Created by Monty on 5/7/2017.
 */

public class PersonDetails implements Serializable {
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    private String favouriteColour;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getFavouriteColour() {
        return favouriteColour;
    }

    public void setFavouriteColour(String favouriteColour) {
        this.favouriteColour = favouriteColour;
    }
}
