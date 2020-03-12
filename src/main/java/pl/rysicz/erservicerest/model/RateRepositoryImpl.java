package pl.rysicz.erservicerest.model;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Configuration
@ComponentScan(basePackages = {"pl.rysicz.erservicerest.model",
        "pl.rysicz.erservicerest.controller"})
public class RateRepositoryImpl implements RateRepository {

    private static final String fileName = "rate-data.json";
    private static final ClassLoader classLoader = RateRepository.class.getClassLoader();

    private static File loadDb() {
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
    public Boolean save(String content) throws IOException {
        File file = loadDb();
        try {
            Files.write(
                    file.toPath(),
                    content.getBytes(),
                    StandardOpenOption.WRITE);
        } catch (IOException e) {
            throw new IOException(e);
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
