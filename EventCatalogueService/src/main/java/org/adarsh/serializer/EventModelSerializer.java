package org.adarsh.serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.adarsh.model.EventModel;
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
