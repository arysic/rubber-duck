package pl.rysicz.erservicerest.controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.rysicz.erservicerest.model.Rate;
import pl.rysicz.erservicerest.model.RateRepositoryImpl;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class RateController {

    private RateRepositoryImpl rateRepository;

    RateController(RateRepositoryImpl rateRepository) {
        this.rateRepository = rateRepository;
    }

    @GetMapping(path = "/api")
    public List<Rate> getAll() throws Exception {
        String data = String.join("", rateRepository.fetchData());
        Type listType = new TypeToken<ArrayList<Rate>>(){}.getType();
        return new Gson().fromJson(data, listType);
    }

    @GetMapping(path = "/api/{date}/{base}")
    public ResponseEntity<Rate> getOne(@PathVariable("date") String pDate, @PathVariable("base") String pBase) throws Exception {
        if (!pBase.equals("PLN")) throw new FuncUnhandledException(pBase);
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(pDate);
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
            rateRepository.save(new Gson().toJson(rateList));
        }
    }
}
