package config;

import base.UserCreate;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import utils.InputData;

public abstract class UserDataTestConfig {
    public String accessToken;
    public Faker faker;
    public Response response;
    public Response updateResponse;
    public UserCreate userCreate;

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
        faker = new Faker();
        userCreate = InputData.createRandomUser();
        response = Responses.userCreateResponse(userCreate);
        accessToken = response.path("accessToken");
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            Responses.deleteUserResponse(accessToken);
            System.out.println("Пользователь удален");
        }
    }
}
