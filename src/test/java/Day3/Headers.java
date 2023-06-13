package Day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class Headers
{
    //get headers from google.com
    @Test
    void getHeaders()
    {
        try
        {
            given()
                    .when()
                    .get("https://www.google.com/")
                    .then()
                    .header("Server","gws")
                    .header("Content-Encoding","gzip")
                    .header("Cache-Control","private, max-age=0")
                    .header("Transfer-Encoding","chunked")
                    .log().headers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get a single header from google.com
    @Test
    void getSingleHeader() {
        try
        {
            Response headerResponse = given()
                    .when()
                    .get("https://www.google.com/");

            String headerValue = headerResponse.getHeader("Server");
            System.out.println("Server header value = " + headerValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}