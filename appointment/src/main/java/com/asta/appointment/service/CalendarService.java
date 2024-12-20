package com.asta.appointment.service;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarListEntry;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarService {
    @Autowired
    private Calendar calendar;

    //Création d'un calendrier
    public CalendarDatacontract createCalendar(String practitionerId){
        try{
            com.google.api.services.calendar.model.Calendar newCalendar = new com.google.api.services.calendar.model.Calendar();
            newCalendar.setSummary(practitionerId);

            return CalendarDatacontract.fromGoogleCalendar(this.calendar.calendars().insert(newCalendar).execute());
        }
        catch(Exception e){
            return null;
        }
    }

    //Récupérer le calendrier d'un praticien
    public CalendarDatacontract getCalendar(String practitionerId){
        try{
            List<CalendarListEntry> items = this.calendar.calendarList().list().execute().getItems();

            for(CalendarListEntry item : items){
                // find calendar
                if(item.getSummary().equals(practitionerId)){
                    CalendarDatacontract calendar = CalendarDatacontract.fromGoogleCalendar(this.calendar.calendars().get(item.getId()).execute());

                    // fill calendar with his events
                    this.calendar.events().list(calendar.id).execute().getItems().forEach(event -> calendar.events.add(CalendarDatacontract.EventDatacontract.fromGoogleEvent(event)));

                    return calendar;
                }
            }

            return this.createCalendar(practitionerId);
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }

    //Ajout d'un rendez-vous dans le calendrier
    public CalendarDatacontract addAppointment(String practitionerId, String date, String reason,String patientId){
        try {
            CalendarDatacontract calendar = this.getCalendar(practitionerId);

            if (calendar == null)
                calendar = this.createCalendar(practitionerId);

            if (calendar == null)
                return null;

            Event event = new Event()
                    .setSummary(patientId)
                    .setDescription(reason)
                    .setStart(new EventDateTime().setDateTime(new DateTime(date)));

            Event createdEvent =this.calendar.events().insert(calendar.id,event).execute();

            calendar.events.add(CalendarDatacontract.EventDatacontract.fromGoogleEvent(createdEvent));

            return calendar;
        }
        catch(Exception e){
            System.out.println(e);
            return null;
        }
    }
}
