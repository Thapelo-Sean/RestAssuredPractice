package Day3;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class pathAndQueryParameters
{
    @Test
    void pathAndQueryParameter()
    {
        given()
                    .pathParams("myPath","users") //path parameter
                    .queryParam("page",2)    // query Parameter
                    .queryParam("id",5)
                .when()
                    .get("https://reqres.in/api/{myPath}")
                .then()
                    .statusCode(200)
                    .log().all();
    }
}
