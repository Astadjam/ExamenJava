package com.asta.appointment.service;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Calendar;
import com.google.api.services.calendar.model.Event;

import java.util.ArrayList;
import java.util.List;

public class CalendarDatacontract {
    public String practitionerId;
    public String id;
    public List<EventDatacontract> events = new ArrayList<EventDatacontract>();

    public static CalendarDatacontract fromGoogleCalendar(Calendar calendar) {
        CalendarDatacontract data = new CalendarDatacontract();

        data.id = calendar.getId();
        data.practitionerId = calendar.getSummary();

        return data;
    }

    public static class EventDatacontract {
        public String id;
        public String patientId;
        public String reason;
        public DateTime date;

        public static EventDatacontract fromGoogleEvent(Event event){
            EventDatacontract data = new EventDatacontract();

            data.id = event.getId();
            data.patientId = event.getSummary();
            data.reason = event.getDescription();
            data.date = event.getStart().getDateTime();
            return data;
        }
    }
}
