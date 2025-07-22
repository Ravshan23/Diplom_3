package client;

import io.qameta.allure.Step;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.ValidatableResponse;
import model.Credentials;
import model.User;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class StellarBurgerClient {

    private String baseUri;


    public StellarBurgerClient(String baseUri) {
        this.baseUri = baseUri;
    }

    @Step("Регистрация юзера")
    public ValidatableResponse registerUser(User user) {
        ValidatableResponse response = given()
                .filter(new AllureRestAssured())
                .log()
                .all()
                .baseUri(baseUri)
                .header("Content-Type", "application/json")
                .body(user)
                .post("api/auth/register")
                .then()
                .log()
                .all();
        return response;
    }

    @Step("Логин юзера")
    public ValidatableResponse loginUser(Credentials credentials) {
        ValidatableResponse response = given()
                .filter(new AllureRestAssured())
                .log()
                .all()
                .baseUri(baseUri)
                .header("Content-Type", "application/json")
                .body(credentials)
                .post("api/auth/login")
                .then()
                .log()
                .all();
        return response;
    }

    @Step("Удаление юзера")
    public ValidatableResponse deleteUser(String accessToken) {
        return given()
                .filter(new AllureRestAssured())
                .log()
                .all()
                .baseUri(baseUri)
                .header("Content-Type", "application/json")
                .header("Authorization", accessToken)
                .delete("api/auth/user")
                .then()
                .log()
                .all()
                .statusCode(202)
                .body("success", equalTo(true))
                .body("message", equalTo("User successfully removed"));
    }
}
