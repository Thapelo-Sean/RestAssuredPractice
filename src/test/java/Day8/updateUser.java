package Day8;

import com.github.javafaker.Faker;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class updateUser {

    @Test
    void testUpdateUserChaining(ITestContext context)
    {
        String bearerToken = "c0710e4f5df6a587d633638232d69e4c1d9b6d6e9daab12cee9e4d016e5c6f35";
        int id = (int) context.getSuite().getAttribute("user_id");

        Faker userFaker = new Faker();
        JSONObject data = new JSONObject();

        data.put("name",userFaker.name().fullName());
        data.put("email",userFaker.internet().emailAddress());
        data.put("gender", "female");
        data.put("status", "active");

        given()
                    .contentType("application/json")
                    .headers("Authorization", "Bearer " + bearerToken)
                    .body(data.toString())
                    .pathParam("id", id)
                .when()
                    .put("https://gorest.co.in/public/v2/users/{id}")
                .then()
                    .statusCode(200)
                    .log().all();
    }
}