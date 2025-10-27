package com.adarsh.EventIngestionService.serializer;

import com.adarsh.EventIngestionService.model.EventModel;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

public class EventModelSerializer implements Serializer<EventModel> {
    @Override
    public byte[] serialize(String topic, EventModel data) {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try{
            retVal = objectMapper.writeValueAsString(data).getBytes();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return retVal;
    }
}
