package com.hal9000.moviebrowseapp;

import java.io.Serializable;
import java.util.Calendar;

public class MovieActor implements Serializable{
    private String name, surname;
    private Calendar birthDate;

    MovieActor(String name, String surname, Calendar birthDate){
        this.name = name;
        this.surname = surname;
        this.birthDate = (Calendar) birthDate.clone();
    }

    public String getFullName() { return name + " " + surname; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        Calendar today = Calendar.getInstance();
        int age = today.get(Calendar.YEAR) - birthDate.get(Calendar.YEAR);
        if (today.get(Calendar.MONTH) < birthDate.get(Calendar.MONTH) ||
                (today.get(Calendar.MONTH) == birthDate.get(Calendar.MONTH) && today.get(Calendar.DATE) < birthDate.get(Calendar.DATE))) {
            age--;
        }
        return age;
    }
}
