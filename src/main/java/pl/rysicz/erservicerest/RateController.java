package pl.rysicz.erservicerest;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
public class RateController {

    @GetMapping(path = "/api")
    public List<Rate> getAll() throws Exception {
        String data = String.join("", DbManager
                .readFromFile());
        Type listType = new TypeToken<ArrayList<Rate>>(){}.getType();
        return new Gson().fromJson(data, listType);
    }

    @GetMapping(path = "/api/{date}/{base}")
    public ResponseEntity<Rate> getOne(@PathVariable("date") String pDate, @PathVariable("base") String pBase) throws Exception {
        if (!pBase.equals("PLN")) throw new FuncUnhandledException(pBase);
        Date date = new SimpleDateFormat("yyyy-MM-dd").parse(pDate);
        Rate rateByDate = getRateByDate(date, getAll());
        if (rateByDate == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.accepted().body(rateByDate);
    }

    @PostMapping("/api")
    public void addRate(@RequestBody Rate newRate) throws Exception {
        List<Rate> rateList = getAll();
        if (getRateByDate(newRate.getDate(), rateList) == null) {
            rateList.add(newRate);
            DbManager.writeToFile(new Gson().toJson(rateList));
        }
    }

    private Rate getRateByDate(Date date, List<Rate> rateList) {
        return getRateMap(rateList)
                .get(date);
    }

    private Map<Date,Rate> getRateMap(List<Rate> rateList) {
        return rateList
                .stream()
                .collect(Collectors.toMap(Rate::getDate, rate -> rate));
    }
}
