package com.yuvraj.util.json;

/**
 * @author Yuvraj
 */
public interface Json {
    String encode(Object object);

    <T> T decode(String json, Class<T> type);

    <T> T convert(Object object, Class<T> type);

    static Json create() {
        return new JacksonJson();
    }
}
