package pl.rysicz.erservicerest;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;
import java.util.List;
import java.util.Objects;

public class DbManager {

    private static final String fileName = "rate-data.json";
    private static final ClassLoader classLoader = DbManager.class.getClassLoader();

    public static File loadDb() {
        return new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
    }

    public static List<String> readFromFile() throws IOException {
        File file = loadDb();
        return Files.readAllLines(file.toPath());
    }

    public static void writeToFile(String content) throws IOException {
        File file = loadDb();
        Files.write(
                file.toPath(),
                content.getBytes(),
                StandardOpenOption.WRITE);
    }
}
