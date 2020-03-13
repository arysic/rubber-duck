package pl.rysicz.erservicerest.model;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class RateRepositoryImpl implements RateRepository {

    private static final String fileName = "rate-data.json";
    private static final ClassLoader classLoader = RateRepository.class.getClassLoader();

    private File loadDb() {
        return new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
    }

    private String readFromFile(File file) throws IOException {
        return Files.readString(file.toPath());
    }

    @Override
    public String fetchData() throws IOException {
        File file = loadDb();
        return readFromFile(file);
    }

    @Override
    public boolean save(String content) {
        File file = loadDb();
        try {
            Files.write(
                    file.toPath(),
                    content.getBytes(),
                    StandardOpenOption.WRITE);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return true;
    }

    @Override
    public Rate getRateByDate(Date date, List<Rate> rateList) {
        return getRateMap(rateList)
                .get(date);
    }

    @Override
    public Map<Date,Rate> getRateMap(List<Rate> rateList) {
        return rateList
                .stream()
                .collect(Collectors.toMap(Rate::getDate, rate -> rate));
    }
}
