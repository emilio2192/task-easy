package com.taskeasy.meeting;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class ResponseList {

    LocalTime start;
    LocalTime end;
    List<String> persons;

    public ResponseList(Date start, Date end, List<String> persons) {
        LocalTime startInput = start.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        LocalTime endInput = end.toInstant().atZone(ZoneId.systemDefault()).toLocalTime();
        this.start = startInput;
        this.end = endInput;
        this.persons = persons;
    }

    public LocalTime getStart() {
        return start;
    }

    public void setStart(LocalTime start) {
        this.start = start;
    }

    public LocalTime getEnd() {
        return end;
    }

    public void setEnd(LocalTime end) {
        this.end = end;
    }

    public List<String> getPersons() {
        return persons;
    }

    public void setPersons(List<String> persons) {
        this.persons = persons;
    }
}
