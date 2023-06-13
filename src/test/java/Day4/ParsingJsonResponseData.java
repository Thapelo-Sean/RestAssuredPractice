package Day4;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ParsingJsonResponseData
{
    //Approach 1

    @Test
    void testJsonResponse1()
    {
        try
        {
            given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("https://simple-books-api.glitch.me/books")
                    .then()
                    .statusCode(200)
                    .header("Content-Type","application/json; charset=utf-8")
                    .header("Content-Length","417")
                    .header("Connection","keep-alive")
                    .header("x-powered-by","Express")
                    .header("etag","W/\"1a1-MfqhfTYtZO2sguD1mJq8Vf41WjU\"")
                    .body("[0].id",equalTo(1))
                    .body("[0].name",equalTo("The Russian"))
                    .body("[0].type",equalTo("fiction"))
                    .body("[0].available",equalTo(true))
                    .log().all();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Approach 2 (creating a Response variable)
    @Test
    void testJsonResponse()
    {
        try
        {
            Response jsonResponse = given()
                    .contentType(ContentType.JSON)
                    .when()
                    .get("https://simple-books-api.glitch.me/books");

            String bookName = jsonResponse.jsonPath().get("[0].name").toString();
            Integer bookId = jsonResponse.jsonPath().getInt("[0].id");
            Assert.assertEquals(jsonResponse.header("Content-Type"),"application/json; charset=utf-8");
            Assert.assertEquals(jsonResponse.getStatusCode(),200);
            Assert.assertEquals(bookId,1);
            Assert.assertEquals(bookName,"The Russian");
            String bookNames = jsonResponse.path("findAll{it.name}.name").toString();
            System.out.println(bookNames);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//jsonRes.path("findAll{it.name}.name").toString()