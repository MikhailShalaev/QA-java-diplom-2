package config;

import base.UserCreate;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import utils.InputData;

public abstract class UserCreateTestConfig {
    public Response response;
    public Response responseDouble;
    public UserCreate userCreate;
    public UserCreate userCreateDouble;
    public String accessToken;

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
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
