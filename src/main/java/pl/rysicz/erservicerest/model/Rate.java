package pl.rysicz.erservicerest.model;

import lombok.*;

import java.time.LocalDate;
import java.util.Map;

@Data
public class Rate {

    private LocalDate           date;
    private String              base = "PLN";
    private Map<String, Double> rates;

}
