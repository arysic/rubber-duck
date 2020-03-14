package pl.rysicz.erservicerest.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@ComponentScan(basePackages = {"pl.rysicz.erservicerest.model",
        "pl.rysicz.erservicerest.controller",
        "pl.rysicz.erservicerest.configuration"})
public class WebAppConfig {

    public Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateJsonDeserializer());
        Gson gson = gsonBuilder.create();
        return gson;
    }
}
