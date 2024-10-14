package com.yuvraj.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author Yuvraj
 */
public class JacksonJson implements Json {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public String encode(Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new JsonException("Could not encode" , e);
        }
    }

    @Override
    public <T> T decode(String json, Class<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (Exception e) {
            throw new JsonException("Could not decode" , e);
        }
    }

    @Override
    public <T> T convert(Object object, Class<T> type) {
        try {
            return mapper.convertValue(object, type);
        } catch (Exception e) {
            throw new JsonException("Could not convert" , e);
        }
    }

    static {
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, false);
    }
}
