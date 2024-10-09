package utils;

import base.UserCreate;
import com.github.javafaker.Faker;


public class InputData {
    private String name;
    private String password;
    private String email;

    public static UserCreate createRandomUser() {
        Faker faker = new Faker();
        return new UserCreate(faker.internet().emailAddress(), faker.internet().password(), faker.name().username());
    }

    public static UserCreate createUser(String email, String password, String name) {
        return new UserCreate(email, password, name);
    }

}


