package pl.rysicz.erservicerest;

import lombok.*;

import java.util.Date;
import java.util.Map;

@Data
public class Rate {

    private Date                date;
    private String              base = "PLN";
    private Map<String, Double> rates;

}
