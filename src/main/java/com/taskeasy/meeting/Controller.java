package com.taskeasy.meeting;


import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
public class Controller {
    public static List<Schedule> list = new ArrayList<Schedule>();

    @RequestMapping(value = "/available", method = RequestMethod.POST)
    @ResponseBody
    public List<ResponseList> available(@RequestBody List<Person> payload) {
        Controller.initSchedule();
        // Controller.addPersonEnabled(payload);
        Controller.evaluate(payload);
        List<ResponseList> responseList = new ArrayList<ResponseList>();
        for (Integer i = 0; i < list.size(); i++) {
            System.out.println("Initial # " + list.get(i).getStart() + " - End - " + list.get(i).getEnd() + " ########### " + list.get(i).getPersons());
            if(list.get(i).getPersons().size()> 2){
                ResponseList object = new ResponseList(list.get(i).getStart(), list.get(i).getEnd(), list.get(i).getPersons());
                responseList.add(object);
            }
        }
        return responseList;
    }


    /**
     * initSchedule is for init list for save each person
     * who is available on start and end range
     */

    public static void initSchedule() {
        // date to evaluate to the intervals item
        Date dateEvaluate = Controller.parseDate("08:00");
        // times to evaluate available
        Date initDate = Controller.parseDate("08:00");
        Date startLunch = Controller.parseDate("12:00");
        Date endLunch = Controller.parseDate("13:00");
        Date finishDate = Controller.parseDate("17:00");
        Calendar cal = Calendar.getInstance();
        // fill all range of the >= 8:00 and <= 17:00
        // the interval is a half hour
        for (Integer i = 1; i < 19; i++) {
            // set the actual time
            cal.setTime(dateEvaluate);
            // add a half hour
            cal.add(Calendar.MINUTE, 30);
            if ((dateEvaluate.compareTo(initDate) >= 0 && dateEvaluate.compareTo(startLunch) <= 0) || (dateEvaluate.compareTo(endLunch) >= 0 && dateEvaluate.compareTo(finishDate) <= 0)) {
                if (cal.getTime().compareTo(startLunch) <= 0 || cal.getTime().compareTo(endLunch) >= 0) {
                    Schedule schedule = new Schedule(dateEvaluate, cal.getTime(), new ArrayList<String>());
                    list.add(schedule);
                }

            }
            // set the new time to evaluate
            dateEvaluate = cal.getTime();
        }
    }

    /**
     * is for parse from string to date
     */

    public static Date parseDate(String dateParse) {
        DateFormat sdf = new SimpleDateFormat("HH:mm");
        try {
            return sdf.parse(dateParse);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new Date();
    }

    public static void evaluate(List<Person> people) {
        DateFormat sdf = new SimpleDateFormat("HH:mm");
        Calendar cal = Calendar.getInstance();
        for (Integer i = 0; i < list.size(); i++) {
            for (Integer p = 0; p < people.size(); p++) {
                String[] schedule = people.get(p).getSchedule();
                for (Integer j = 0; j < schedule.length; j++) {
                    if(list.get(i).getPersons().contains(people.get(p).getName()) == false){
                        list.get(i).getPersons().add(people.get(p).getName());
                    }
                }
            }
        }

        for (Integer i = 0; i < list.size(); i++) {
            for (Integer p = 0; p < people.size(); p++) {
                String[] schedule = people.get(p).getSchedule();
                for (Integer j = 0; j < schedule.length; j++) {
                    Date initMeeting = Controller.parseDate(schedule[j]);
                    cal.setTime(initMeeting);
                    cal.add(Calendar.MINUTE, 30);
                    Date endMeeting = cal.getTime();
                    Date initRange = list.get(i).getStart();
                    Date finalRange = list.get(i).getEnd();
                    if(initMeeting.compareTo(initRange) == 0 && endMeeting.compareTo(finalRange) == 0 ){
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
        System.out.println("INDEX " + index + " --> " + newList + " ------ OLD LIST  ------ " + list.get(index).getPersons());
        list.get(index).setPersons(newList);
        System.out.println("ACTUAL LIST " + list.get(index).getPersons());


    }
}