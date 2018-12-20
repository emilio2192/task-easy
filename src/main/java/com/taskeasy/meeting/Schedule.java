package com.taskeasy.meeting;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class Schedule {
    Date start;
    Date end;
    List<String> persons;

    public Schedule(Date start, Date end, List<String> persons) {
        this.start = start;
        this.end = end;
        this.persons = persons;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    public List<String> getPersons() {
        return persons;
    }

    public void setPersons(List<String> persons) {
        this.persons.clear();
        this.persons = persons;
    }
}
