package pl.rysicz.erservicerest.configuration;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
@ComponentScan(basePackages = {"pl.rysicz.erservicerest"})
public class AppConfig {

    @Bean
    public Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateJsonDeserializer());
        Gson gson = gsonBuilder.create();
        return gson;
    }
}
