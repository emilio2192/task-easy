package com.taskeasy.meeting.utils;

import com.taskeasy.meeting.beans.Schedule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Util {

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

    /**
     * initSchedule is for init list for save each person
     * who is available on start and end range
     */

    public static List<Schedule> initSchedule() {
        List<Schedule> list = new ArrayList<Schedule>();
        // date to evaluate to the intervals item
        Date dateEvaluate = Util.parseDate("08:00");
        // times to evaluate available
        Date initDate = Util.parseDate("08:00");
        Date startLunch = Util.parseDate("12:00");
        Date endLunch = Util.parseDate("13:00");
        Date finishDate = Util.parseDate("17:00");
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
        return list;
    }

    public static Date addHalfHour(Date date){
        Calendar cal =  Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.MINUTE, 30);
        return cal.getTime();
    }
}
