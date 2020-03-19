package pl.rysicz.erservicerest.model;

import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
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
        return fetchData(file);
    }

    @Override
    public String fetchData(File file) throws IOException {
        return readFromFile(file);
    }

    @Override
    public boolean save(String content) {
        return save(content, loadDb());
    }

    @Override
    public boolean save(String content, File file) {
        try {
            Files.write(
                    file.toPath(),
                    content.getBytes(),
                    StandardOpenOption.WRITE);
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public Rate getRateByDate(LocalDate date, List<Rate> rateList) {
        return getRateMap(rateList)
                .get(date);
    }

    @Override
    public Map<LocalDate,Rate> getRateMap(List<Rate> rateList) {
        return rateList
                .stream()
                .collect(Collectors.toMap(Rate::getDate, rate -> rate));
    }
}
