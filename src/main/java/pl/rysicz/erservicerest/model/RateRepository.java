package pl.rysicz.erservicerest.model;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface RateRepository {

    String fetchData() throws IOException;

    String fetchData(File file) throws IOException;

    boolean save(String content);

    boolean save(String content, File file);

    Rate getRateByDate(LocalDate date, List<Rate> rateList);

    Map<LocalDate,Rate> getRateMap(List<Rate> rateList);
}
