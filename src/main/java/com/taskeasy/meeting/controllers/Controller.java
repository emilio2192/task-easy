package com.taskeasy.meeting.controllers;


import com.taskeasy.meeting.beans.Person;
import com.taskeasy.meeting.utils.ResponseList;
import com.taskeasy.meeting.beans.Schedule;
import com.taskeasy.meeting.utils.Util;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
public class Controller {
    public static List<Schedule> list;
    public static Util tools = new Util();

    @RequestMapping(value = "/available", method = RequestMethod.POST)
    @ResponseBody
    public List<ResponseList> available(@RequestBody List<Person> payload) {
        list = tools.initSchedule();
        Controller.evaluate(payload);
        List<ResponseList> responseList = new ArrayList<ResponseList>();
        for (Integer i = 0; i < list.size(); i++) {
            //evaluate if have more than 3 persons available
            if (list.get(i).getPersons().size() > 2) {
                // create list pretty for return
                ResponseList object = new ResponseList(list.get(i).getStart(), list.get(i).getEnd(), list.get(i).getPersons());
                responseList.add(object);
            }
        }
        return responseList;
    }

    public static void evaluate(List<Person> people) {
        // set all persons in the list available
        for (Integer i = 0; i < list.size(); i++) {
            for (Integer p = 0; p < people.size(); p++) {
                String[] schedule = people.get(p).getSchedule();
                if (list.get(i).getPersons().contains(people.get(p).getName()) == false) {
                    list.get(i).getPersons().add(people.get(p).getName());
                }
            }
        }
        // remove persons who have a meeting
        for (Integer i = 0; i < list.size(); i++) {
            for (Integer p = 0; p < people.size(); p++) {
                String[] schedule = people.get(p).getSchedule();
                for (Integer j = 0; j < schedule.length; j++) {
                    Date initMeeting = tools.parseDate(schedule[j]);
                    Date endMeeting = tools.addHalfHour(initMeeting);
                    Date initRange = list.get(i).getStart();
                    Date finalRange = list.get(i).getEnd();
                    // evaluate if have meeting and remove for the list available
                    if (initMeeting.compareTo(initRange) == 0 && endMeeting.compareTo(finalRange) == 0) {
                        Controller.removeItemFromPersons(people.get(p).getName(), list.get(i).getPersons(), i);
                    }
                }
            }
        }
    }

    public static void removeItemFromPersons(String name, List<String> listPeople, Integer index) {
        List<String> newList = new ArrayList<String>();
        for (Integer i = 0; i < listPeople.size(); i++) {
            if (!listPeople.get(i).contains(name)) {
                newList.add(listPeople.get(i));
            }
        }
        list.get(index).setPersons(newList);
    }
}