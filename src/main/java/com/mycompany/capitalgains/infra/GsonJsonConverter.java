package com.mycompany.capitalgains.infra;

import com.google.gson.Gson;
import com.mycompany.capitalgains.model.JsonConverter;

public class GsonJsonConverter implements JsonConverter {
    private final Gson gson;

    public GsonJsonConverter() {
        this.gson = new Gson();
    }

    @Override
    public <T> T fromJson(String json, Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }

    @Override
    public String toJson(Object object) {
        return gson.toJson(object);
    }
}