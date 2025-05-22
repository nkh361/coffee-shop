package edu.depaul.coffeeapp.tools;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

@Component
public class JsonConverter {
    private final ObjectMapper mapper;

    public JsonConverter(ObjectMapper objectMapper) {
        this.mapper = objectMapper;
    }

    public <T> T fromJson(String json, Class<T> clazz) throws Exception{
        try {
            return mapper.readValue(json, clazz);
        } catch (JsonProcessingException e) {
            throw new Exception("Json conversion failed: ", e);
        }
    }
}
