package Day7;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class Authentications

    /*Basic Authentication
    Digest Authentication
    Preemptive Authentication
    Bearer token Authentication
    Oauth 1.0, 2.0 Authentication
    API Key Authentication*/
{
    //Basic Authentication
    @Test
    void testBasicAuthentication()
    {
        try
        {
            given()
                    .auth().basic("postman","password")
                    .when()
                    .get("https://postman-echo.com/basic-auth")
                    .then()
                    .statusCode(200)
                    .body("authenticated",equalTo(true))
                    .log().all();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Digest Authentication
    @Test
    void testDigestAuthentication()
    {
        try
        {
            //Digest authentication
            given()
                    .auth().digest("postman","password")
                    .when()
                    .get("https://postman-echo.com/digest-auth")
                    .then()
                    .statusCode(200)
                    .body("authenticated",equalTo(true))
                    .log().all();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Preemptive Authentication
    @Test
    void testPreemptiveAuthentication()
    {
        try
        {
            given()
                    .auth().preemptive().basic("postman","password")
                    .when()
                    .get("https://postman-echo.com/basic-auth")
                    .then()
                    .statusCode(200)
                    .body("authenticated", equalTo(true))
                    .log().all();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    void testBearerToken()
    {
        try
        {
            String bearerToken = "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";

            given()
                    .headers("authentication", "bearer" + bearerToken)
                    .when()
                    .get("http://api.github.com/users/repos")
                    .then()
                    .statusCode(200)
                    .log().all();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}