package Day8;

import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class deleteUser {
    @Test
    void testDeleteUserChaining(ITestContext context)
    {
        try
        {
            String bearerToken = "c0710e4f5df6a587d633638232d69e4c1d9b6d6e9daab12cee9e4d016e5c6f35";
            int id = (Integer) context.getSuite().getAttribute("user_id");

            given()
                    .headers("Authorization", "Bearer " + bearerToken)
                    .pathParam("id", id)
                    .when()
                    .delete("https://gorest.co.in/public/v2/users/{id}")
                    .then()
                    .statusCode(204);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}