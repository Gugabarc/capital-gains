package com.mycompany.capitalgains.model;

public interface JsonConverter {
    <T> T fromJson(String json, Class<T> clazz);
    String toJson(Object object);
}
