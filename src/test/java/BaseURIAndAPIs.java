import io.restassured.RestAssured;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.notNullValue;

public class BaseURIAndAPIs {

    public static final String MAIN_PAGE = "https://stellarburgers.nomoreparties.site";
    public static final String REGISTERATION_PAGE = "https://stellarburgers.nomoreparties.site/register";
    private static final String REGISTER_API = "/api/auth/register";
    private static final String LOGIN_API = "/api/auth/login";
    private static final String DELETE_USER_API = "/api/auth/user";

    public BaseURIAndAPIs() {
        RestAssured.baseURI = "https://stellarburgers.nomoreparties.site/";
    }

    public String registerAndGetToken(UserData userData) {
        return given()
                .header("Content-type", "application/json")
                .body(userData)
                .post(REGISTER_API)
                .then()
                .statusCode(200)
                .body("success", notNullValue())
                .extract()
                .path("accessToken")
                .toString()
                .replace("Bearer ", "");
    }

    public String loginAndGetToken(UserData userData) {
        return given()
                .header("Content-type", "application/json")
                .body(userData)
                .post(LOGIN_API)
                .then()
                .statusCode(200)
                .body("success", notNullValue())
                .extract()
                .path("accessToken")
                .toString()
                .replace("Bearer ", "");
    }

    public void deleteUser(String authToken) {
        given()
            .header("Content-type", "application/json")
            .auth().oauth2(authToken)
            .delete(DELETE_USER_API)
            .then()
            .statusCode(202);;
    }
}
