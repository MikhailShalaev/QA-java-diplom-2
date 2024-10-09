package config;

import base.Order;
import base.UserCreate;
import base.UserLogin;
import io.qameta.allure.Step;
import io.restassured.response.Response;

import static config.Constants.*;
import static io.restassured.RestAssured.given;

public class Responses {
    @Step("Регистрируем нового пользователя")
    public static Response userCreateResponse(UserCreate userCreate) {
        return given()
                .header("Content-type", "application/json")
                .body(userCreate)
                .when()
                .post(CREATE_USER_EP);
    }
    @Step("Авторизуем пользователя")
    public static Response userLoginResponse(UserLogin userLogin) {
        return given()
                .header("Content-type", "application/json")
                .body(userLogin)
                .when()
                .post(LOGIN_USER_EP);
    }
    @Step("Изменяем адрес электронной почты пользователя")
    public static Response userChangeUserEmail(String accessToken, String email) {
        String data = String.format("{\"email\": \"%s\"}", email);
        return given()
                .header("Content-type", "application/json")
                .header("authorization", accessToken)
                .body(data)
                .when()
                .patch(UPDATE_USER_EP);
    }
    @Step("Изменяем имя пользователя")
    public static Response userChangeUserName(String accessToken, String name) {
        String data = String.format("{\"name\": \"%s\"}", name);
        return given()
                .header("Content-type", "application/json")
                .header("authorization", accessToken)
                .body(data)
                .when()
                .patch(UPDATE_USER_EP);
    }
    @Step("Удаляем пользователя")
    public static Response deleteUserResponse(String accessToken) {
        return given()
                .header("authorization", accessToken)
                .when()
                .delete(DELETE_USER_EP);

    }
    @Step("Создаем заказ")
    public static Response createOrderResponse(String accessToken, Order order) {
        return given()
                .header("Content-type", "application/json")
                .header("authorization", accessToken)
                .body(order)
                .when()
                .post(CREATE_ORDER_EP);
    }
    @Step("Получаем список ингредиентов")
    public static Response getIngredientsListResponse() {
        return given()
                .header("Content-type", "application/json")
                .when()
                .get(GET_INGREDIENTS_EP);
    }
    @Step("Получаем список заказов конкретного пользователя")
    public static Response getUserOrdersResponse(String accessToken) {
        return given()
                .header("authorization", accessToken)
                .when()
                .get(GET_USER_ORDERS_EP);
    }

}

