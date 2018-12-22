package com.taskeasy.meeting.beans;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;

public class Person {
    private String name;
    private String[] schedule;
    private Boolean available;

    public Person(String name, String[] schedule) {
        this.name = name;
        Arrays.sort(schedule);
        this.schedule = schedule;
        this.available = true;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String[] getSchedule() {
        return schedule;
    }

    public void setSchedule(String[] schedule) {
        this.schedule = schedule;
    }

    public Boolean getAvailable() {
        return available;
    }

    public void setAvailable(Boolean available) {
        this.available = available;
    }

    public Date parseSchedule(Integer indexSchedule) {
        DateFormat sdf = new SimpleDateFormat("hh:mm");
        try {
            Date date = sdf.parse(this.schedule[indexSchedule]);
            return date;
        } catch (Exception e) {
            e.printStackTrace();
            return new Date();
        }

    }
}
