package com.adarsh.EventIngestionService.controller;

import com.adarsh.EventIngestionService.model.EventModel;
import com.adarsh.EventIngestionService.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("event-ingestion-service/api/v1/events")
@CrossOrigin(origins = "*")
public class EventIngestionController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<EventModel> createOrUpdateEvent(@RequestBody EventModel eventModel ){
        try{
            EventModel event = eventService.createOrUpdateEvent(eventModel);
            return new ResponseEntity<>(event, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}