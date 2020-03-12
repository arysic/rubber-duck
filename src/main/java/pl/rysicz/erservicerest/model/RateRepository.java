package pl.rysicz.erservicerest.model;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface RateRepository {

    String fetchData() throws IOException;

    Boolean save(String content) throws IOException;

    Rate getRateByDate(Date date, List<Rate> rateList);

    Map<Date,Rate> getRateMap(List<Rate> rateList);
}
