import config.Responses;
import config.UserCreateTestConfig;
import io.qameta.allure.Description;
import org.junit.Test;
import utils.InputData;

import static config.Constants.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserCreateTest extends UserCreateTestConfig {

    @Test
    @Description("Проверка создания пользователя с корректными и полными данными")
    public void userCanBeCreatedWhenCorrectDataProvided() {
        response.then()
                .assertThat()
                .body(SUCCESS, equalTo(true))
                .and()
                .statusCode(OK);
    }

    @Test
    @Description("Проверка невозможности создания пользователя, дублирующего существующего пользователя")
    public void userCanNotBeDuplicated() {
        userCreateDouble = InputData.createUser(response.path("user.email"), response.path("user.name"), response.path("user.name"));
        responseDouble = Responses.userCreateResponse(userCreateDouble);
        responseDouble.then()
                .assertThat()
                .statusCode(FORBIDDEN);
    }

    @Test
    @Description("Проверка невозможности создания пользователя с неполными данными")
    public void UserCanNotBeCreatedIfParameterMissing() {
        userCreate.setName("");
        response = Responses.userCreateResponse(userCreate);
        response.then()
                .assertThat()
                .statusCode(FORBIDDEN);
    }
}
