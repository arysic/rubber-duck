package pl.rysicz.erservicerest.controller;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.time.LocalDate;

class LocalDateJsonDeserializer implements JsonDeserializer<LocalDate> {

    @Override
    public LocalDate deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        return LocalDate.parse(jsonElement.getAsJsonPrimitive().getAsString());
    }
}
