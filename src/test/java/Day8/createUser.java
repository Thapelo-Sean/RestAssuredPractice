package Day8;

import com.github.javafaker.Faker;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;


public class createUser {

    @Test
    void testCreateUserChaining(ITestContext context)
    {
        try
        {
            Faker userFaker = new Faker();
            JSONObject data = new JSONObject();

            data.put("name", userFaker.name().fullName());
            data.put("email", userFaker.internet().emailAddress());
            data.put("gender", "Male");
            data.put("status", "inactive");

            String bearerToken = "c0710e4f5df6a587d633638232d69e4c1d9b6d6e9daab12cee9e4d016e5c6f35";

            int id = given()
                    .contentType("application/json")
                    .headers("Authorization","Bearer "+ bearerToken)
                    .body(data.toString())
                    .when()
                    .post("https://gorest.co.in/public/v2/users")
                    .jsonPath().getInt("id");

            System.out.println("Generated ID is " + id);
            context.getSuite().setAttribute("user_id", id);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}