package com.adarsh.EventIngestionService.service;


import com.adarsh.EventIngestionService.model.EventModel;
import com.adarsh.EventIngestionService.producer.EventProducer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;


@Service
@RequiredArgsConstructor
public class EventService
{
    private static final AtomicLong counter = new AtomicLong(0);

    @Autowired
    private EventProducer eventProducer;

    public EventModel createOrUpdateEvent(EventModel eventModel) {
        long eventId = generateUniqueEventId();

        eventModel.setEventId(eventId);
        eventProducer.sendEventToKafka(eventModel);
        return eventModel;
    }
    public long generateUniqueEventId() {
        long base = System.currentTimeMillis() * 1_000_000 + (System.nanoTime() % 1_000_000);
        long id = counter.getAndIncrement();

        if (id == Long.MAX_VALUE) {
            counter.compareAndSet(Long.MAX_VALUE, 0); // reset safely
        }

        return base + id;
    }
}