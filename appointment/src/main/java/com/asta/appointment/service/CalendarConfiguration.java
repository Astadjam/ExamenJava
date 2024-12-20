package com.asta.appointment.service;

import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.auth.oauth2.ServiceAccountCredentials;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import java.io.FileInputStream;
import java.util.List;

@Configuration
public class CalendarConfiguration {
    @Bean
    public Calendar buildCalendar() {
        try{// load credentials
            var credentials = ServiceAccountCredentials.fromStream(new FileInputStream(new ClassPathResource("credentials.json").getFile())).createScoped(List.of(CalendarScopes.CALENDAR,CalendarScopes.CALENDAR_READONLY));

            // get token
            credentials.refreshIfExpired();
            var token = credentials.getAccessToken().getTokenValue();

            // build service
            HttpTransport httpTransport = new NetHttpTransport();
            return new Calendar.Builder(httpTransport, new GsonFactory(), (request) -> request.getHeaders().set("Authorization", "Bearer " + token)).setApplicationName("appointment").build();
        }
        catch(Exception e){
            return null;
        }
    }

}
