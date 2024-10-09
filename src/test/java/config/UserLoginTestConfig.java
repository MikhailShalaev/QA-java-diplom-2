package config;

import base.UserCreate;
import base.UserLogin;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import utils.InputData;

public abstract class UserLoginTestConfig {
    public String accessToken;
    public Faker faker;
    public Response createResponse;
    public Response loginResponse;
    public UserLogin userLogin;
    public UserCreate userCreate;

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
        userCreate = InputData.createRandomUser();
        createResponse = Responses.userCreateResponse(userCreate);
        userLogin = UserLogin.from(userCreate);
        faker = new Faker();
        accessToken = createResponse.path("accessToken");
    }

    @After
    public void tearDown() {
        accessToken = createResponse.path("accessToken");
        if (accessToken != null) {
            Responses.deleteUserResponse(accessToken);
            System.out.println("Пользователь удален");
        }

    }
}
