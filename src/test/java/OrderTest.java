import base.Order;
import config.OrderTestConfig;
import config.Responses;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.Arrays;

import static config.Constants.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class OrderTest extends OrderTestConfig {

    @Test
    @Description("Проверка возможности создания заказа авторизованным пользователем")
    public void canCreateOrderWhenAuthorized() {
        Response orderCreateResponse = Responses.createOrderResponse(accessToken, order);
        orderCreateResponse.then()
                .assertThat()
                .statusCode(OK)
                .body(SUCCESS, equalTo(true));
    }

    @Test
    @Description("Проверка невозможности создания заказа неавторизованным пользователем")
    public void canNotCreateOrderWhenUnauthorized() {
        accessToken = faker.internet().uuid();
        Response orderCreateResponse = Responses.createOrderResponse(accessToken, order);
        orderCreateResponse.then()
                .assertThat()
                .statusCode(UNAUTHORIZED) //дефект - можно создать заказ без авторизации
                .body(SUCCESS, equalTo(false));

    }

    @Test
    @Description("Проверка невозможности создания заказа без указания ингредиентов")
    public void canNotCreateOrderWithoutIngredientsProvided() {
        order = new Order();
        Response orderCreateResponse = Responses.createOrderResponse(accessToken, order);
        orderCreateResponse.then()
                .assertThat()
                .statusCode(BAD_REQUEST)
                .body(SUCCESS, equalTo(false))
                .body(MESSAGE, equalTo("Ingredient ids must be provided"));
    }
    @Description("Проверка невозможности создания с некорректными хэшами ингредиентов")
    @Test
    public void canNotCreateOrderWithIncorrectIngredientHashes() {
        String id1 = faker.internet().uuid();
        String id2 = faker.internet().uuid();
        idList = Arrays.asList(id1, id2);
        order = new Order(idList);
        Response orderCreateResponse = Responses.createOrderResponse(accessToken, order);
        orderCreateResponse.then()
                .assertThat()
                .statusCode(SERVER_ERROR);
    }

}
