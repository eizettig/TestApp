package com.zettig.a65apps.model.data;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;


public class SpecialtyDeserializer implements JsonDeserializer<Specialty> {
    @Override
    public Specialty deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Specialty specialty = new Specialty();
        JsonObject jsonObject = json.getAsJsonObject();
        specialty.setName(jsonObject.get("name").getAsString());
        specialty.setSpecialtyId(jsonObject.get("specialty_id").getAsInt());
        return specialty;
    }
}
