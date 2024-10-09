import config.GetOrderTestConfig;
import config.Responses;
import io.qameta.allure.Description;
import io.restassured.response.Response;
import org.junit.Test;

import static config.Constants.*;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

public class GetOrderTest extends GetOrderTestConfig {

    @Test
    @Description("Проверка получения списка заказов авторизованного пользователя")
    public void canGetUserOrdersWhenAuthorized() {
        Responses.createOrderResponse(accessToken, order);
        Response getUserOrders = Responses.getUserOrdersResponse(accessToken);
        getUserOrders.then()
                .assertThat()
                .statusCode(OK)
                .body(ORDERS, notNullValue());

    }

    @Test
    @Description("Проверка невозможности получения списка заказов неавторизованного пользователя")
    public void canNotGetUserOrdersWhenUnauthorized() {
        Responses.createOrderResponse(accessToken, order);
        accessToken = faker.internet().uuid();
        Response getUserOrders = Responses.getUserOrdersResponse(accessToken);
        getUserOrders.then()
                .assertThat()
                .statusCode(UNAUTHORIZED)
                .body(SUCCESS, equalTo(false));

    }
}
