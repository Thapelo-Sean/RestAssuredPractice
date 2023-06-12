package Day6;

import io.restassured.matcher.RestAssuredMatchers;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class JsonSchemaValidator
{
    //validate json schema
    @Test
    void jsonSchemaValidator()
    {
        given()
                .when()
                    .get("https://reqres.in/api/unknown")
                .then()
                    .statusCode(200)
                    .assertThat().body(io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath("JsonSchema.json"))
                    .log().all();
    }

    //validate xml schema
    @Test
    void xmlSchemaValidator()
    {
        given()
                .when()
                    .get("http://restapi.adequateshop.com/api/Traveler")
                .then()
                    .statusCode(200)
                    .assertThat().body(RestAssuredMatchers.matchesXsdInClasspath("xmlSchema.xsd"))
                    .log().all();
    }
}
