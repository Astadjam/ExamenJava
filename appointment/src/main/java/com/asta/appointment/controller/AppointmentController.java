package com.asta.appointment.controller;

import com.asta.appointment.service.CalendarDatacontract;
import com.asta.appointment.service.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/appointment")
public class AppointmentController {
    @Autowired
    private CalendarService calendarService;

    @GetMapping("/calendar/{practitionerId}")
    public CalendarDatacontract getCalendar(@PathVariable String practitionerId){
            return this.calendarService.getCalendar(practitionerId);
    }

    @PostMapping("/add")
    public CalendarDatacontract addAppointment(@RequestBody Map<String, Object> requestBody){
        return this.calendarService.addAppointment(
                (String) requestBody.get("practioner-id"),
                (String) requestBody.get("date"),
                (String) requestBody.get("reason"),
                (String) requestBody.get("patient-id")
        );
    }
}
