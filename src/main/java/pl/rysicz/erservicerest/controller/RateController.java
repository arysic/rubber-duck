package pl.rysicz.erservicerest.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rysicz.erservicerest.model.Rate;
import pl.rysicz.erservicerest.model.RateRepository;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.*;

import static java.time.LocalDate.parse;

@RestController
public class RateController {

    private RateRepository rateRepository;

    @Autowired
    RateController(RateRepository rateRepository) {
        this.rateRepository = rateRepository;
    }

    @GetMapping(path = "/api")
    public List<Rate> getAll() throws Exception {
        String data = String.join("", rateRepository.fetchData());
        Type listType = new TypeToken<List<Rate>>(){}.getType();
        return getGson().fromJson(data, listType);
    }

    @GetMapping(path = "/api/{date}/{base}")
    public ResponseEntity<Rate> getOne(@PathVariable("date") String pDate, @PathVariable("base") String pBase) throws Exception {
        if (!pBase.equals("PLN")) {
            throw new IllegalArgumentException("Functionality of getting currency rates for base currency other than PLN has not been handled yet. Invalid base currency parameter" + pBase);
        }
        LocalDate date = parse(pDate);
        Rate rateByDate = rateRepository.getRateByDate(date, getAll());
        if (rateByDate == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.accepted().body(rateByDate);
    }

    @PostMapping("/api")
    public void addRate(@RequestBody Rate newRate) throws Exception {
        List<Rate> rateList = getAll();
        if (rateRepository.getRateByDate(newRate.getDate(), rateList) == null) {
            rateList.add(newRate);
            rateRepository.save(getGson().toJson(rateList));
        }
    }

    private Gson getGson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateAdapter());
        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateJsonDeserializer());
        Gson gson = gsonBuilder.create();
        return gson;
    }
}