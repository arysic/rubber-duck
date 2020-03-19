package pl.rysicz.erservicerest.model;

import org.junit.*;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;

class RateRepositoryImplTest {

    private static final String         fileName    = "resources/rate-data-test.json";
    private static final ClassLoader    classLoader = RateRepository.class.getClassLoader();
    private static final String         content     = "[{\"date\":\"2020-03-03\",\"base\":\"PLN\",\"rates\":{\"EUR\":4.0003,\"USD\":5.5063}]";
    private RateRepositoryImpl          rateRepositoryImpl;
    private File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());

    @Before
    void prepareFile() {        createFile();
        fillFile();
    }

    void createFile() {
        try {
            file.createNewFile();
            System.out.println("Test json db file created.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    void fillFile() {
        assertTrue(rateRepositoryImpl.save(content, file));
    }

    @After
    void deleteFile() {
        file.delete();
        System.out.println("Test json db file deleted");
    }

    @Test
    void fetchData() {
        try {
            assertEquals(rateRepositoryImpl.fetchData(file), content);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}