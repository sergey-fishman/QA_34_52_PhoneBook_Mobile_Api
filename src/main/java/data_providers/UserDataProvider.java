package data_providers;

import dto.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class UserDataProvider {
    private static final String DATA_DIR = "src/test/resources/test_data/";

    @DataProvider
    public Iterator<User> dataProviderPositive() {
        return readUsers("positive_combination.csv").iterator();
    }

    @DataProvider
    public Iterator<User> dataProviderWrongEmail() {
        return readUsers("wrong_username.csv").iterator();
    }

    private List<User> readUsers(String fileName) {
        List<User> userList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader
                (DATA_DIR + fileName))) {
            String line = bufferedReader.readLine();
            while (line != null) {
                String[] parameters = line.split(",");
                userList.add(User.builder()
                        .username(parameters[0])
                        .password(parameters[1])
                        .build());
                line = bufferedReader.readLine();
            }
            return userList;
        } catch (IOException e) {
            throw new RuntimeException("Cannot read test data file: " + fileName, e);
        }
    }
}
