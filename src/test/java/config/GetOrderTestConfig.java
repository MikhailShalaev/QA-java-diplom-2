package config;

import base.Order;
import base.UserCreate;
import com.github.javafaker.Faker;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.After;
import org.junit.Before;
import utils.InputData;

import java.util.List;

public abstract class GetOrderTestConfig {
    public Faker faker;
    public String accessToken;
    public List<String> idList;
    public Order order;

    @Before
    public void setUp() {
        RestAssured.baseURI = Constants.BASE_URL;
        faker = new Faker();
        UserCreate userCreate = InputData.createRandomUser();
        Response response = Responses.userCreateResponse(userCreate);
        accessToken = response.path("accessToken");
        idList = Responses.getIngredientsListResponse()
                .jsonPath()
                .getList("data._id");
        List<String> trimmedIdList = idList.subList(1, 3);
        order = new Order(trimmedIdList);
    }

    @After
    public void tearDown() {
        if (accessToken != null) {
            Responses.deleteUserResponse(accessToken);
            System.out.println("Пользователь удален");
        }
    }
}
