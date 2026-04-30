package com.demowebshop.utilits;

import com.demowebshop.model.Gender;
import com.demowebshop.model.User;
import org.testng.annotations.DataProvider;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class MyDataProviders {

    @DataProvider(name = "negativeRegistrationData")
    public Object[][] negativeRegistrationData() {
        return new Object[][]{
                // Передаем: Объект User, Ожидаемый текст ошибки, Ожидаемое поле с ошибкой (опционально)
                {new User(Gender.MALE, "", "Panchenko", "test@gmail.com", "Pass123!"), "First name is required."},
                {new User(Gender.FEMALE, "Anna", "Smith", "invalid_email", "Pass123!"), "Wrong email"},
                {new User(Gender.MALE, "John", "Doe", "valid@test.com", "123"), "The password should have at least 6 characters."},
                {new User(Gender.MALE, "Existing", "User", "panillich@gmail.com", "SecretPass123!"), "The specified email already exists"}
        };
    }

    @DataProvider(name = "negativeRegistrationFromCsv")
    public Iterator<Object[]> negativeRegistrationFromCsv() throws IOException {
        List<Object[]> list = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/data_users.csv")));
        String line = reader.readLine();
        while (line != null) {
            String[] split = line.split(",");
            Gender gender = split[2].equalsIgnoreCase("Female") ? Gender.FEMALE : Gender.MALE;
            User user = new User(gender, split[0], split[1], split[4], "Password123!");
            list.add(new Object[]{user});
            line = reader.readLine();
        }
        return list.iterator();
    }
}
