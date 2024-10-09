import config.Responses;
import config.UserLoginTestConfig;
import io.qameta.allure.Description;
import org.junit.Test;

import static config.Constants.*;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserLoginTest extends UserLoginTestConfig {

    @Test
    @Description("Проверка авторизации с корректными данными")
    public void canLoginWithCorrectParameters() {
        loginResponse = Responses.userLoginResponse(userLogin);
        loginResponse.then()
                .assertThat()
                .body(SUCCESS, equalTo(true))
                .statusCode(OK);

    }
    @Description("Проверка невозможности авторизации с некорректным адресом почты")
    @Test
    public void canNotLoginWithWrongEmail() {
        userLogin.setEmail(faker.internet().emailAddress());
        loginResponse = Responses.userLoginResponse(userLogin);
        loginResponse.then()
                .assertThat()
                .statusCode(UNAUTHORIZED);
        }

    @Test
    @Description("Проверка невозможности авторизации с некорректным паролем")
    public void canNotLoginWithWrongPassword() {
        userLogin.setEmail(faker.internet().password());
        loginResponse = Responses.userLoginResponse(userLogin);
        loginResponse.then()
                .assertThat()
                .statusCode(UNAUTHORIZED);

    }

    @Test
    @Description("Проверка невозможности авторизации с некорректным паролем и адресом почты")
    public void canNotLoginWhenBothEmailAndPasswordWrong() {
        userLogin.setEmail(faker.internet().emailAddress());
        userLogin.setEmail(faker.internet().password());
        loginResponse = Responses.userLoginResponse(userLogin);
        loginResponse.then()
                .assertThat()
                .statusCode(UNAUTHORIZED);
            }
}
