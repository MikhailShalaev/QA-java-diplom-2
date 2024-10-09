import config.Responses;
import config.UserDataTestConfig;
import io.qameta.allure.Description;
import org.junit.Test;

import static config.Constants.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserDataTest extends UserDataTestConfig {

    @Test
    @Description("Проверка возможности изменения адреса почты авторизованного пользователя")
    public void userEmailCanBeModifiedWhenAuthorized() {
        String newEmail = faker.internet().emailAddress();
        updateResponse = Responses.userChangeUserEmail(accessToken, newEmail);
        updateResponse.then()
                .assertThat()
                .statusCode(OK)
                .body(SUCCESS, equalTo(true))
                .body(EMAIL, equalTo(newEmail));
    }

    @Test
    @Description("Проверка возможности изменения имени авторизованного пользователя")
    public void userNameCanBeModifiedWhenAuthorized() {
        String newName = faker.name().username();
        updateResponse = Responses.userChangeUserName(accessToken, newName);
        updateResponse.then()
                .assertThat()
                .statusCode(OK)
                .body(SUCCESS, equalTo(true))
                .body(NAME, equalTo(newName));
    }

    @Test
    @Description("Проверка невозможности изменения данных пользователя без авторизации")
    public void userDataCanNotBeModifiedWhenUnauthorized() {
        accessToken = faker.internet().uuid();
        updateResponse = Responses.userChangeUserEmail(accessToken, faker.internet().emailAddress());
        updateResponse.then()
                .assertThat()
                .statusCode(UNAUTHORIZED)
                .body(SUCCESS, equalTo(false))
                .body(MESSAGE, equalTo("You should be authorised"));
    }
}