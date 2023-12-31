package Day3;

import io.restassured.response.Response;
import org.testng.annotations.Test;
import java.util.Map;
import static io.restassured.RestAssured.given;

public class Cookies
{
    //get cookies from google.com
    @Test
    void viewCookies()
    {
        try
        {
            given()
                    .when()
                    .get("https://www.google.com/")
                    .then()
                    .log().all();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get a single cookie value
    @Test
    void getCookieValue()
    {
        try
        {
            Response cookieResponse = given()
                    .when()
                    .get("https://www.google.com/");

            String myCookieValue = cookieResponse.getCookie("1P_JAR");
            System.out.println(myCookieValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //get all cookie values
    @Test
    void getAllCookies()
    {
        try
        {
            Response allCookiesResponse = given()
                    .when()
                    .get("https://www.google.com/");

            Map<String,String> cookieValues = allCookiesResponse.getCookies();

            //Traverse through the cookie values

            for (String x: cookieValues.keySet())
            {
                String cookieValue = allCookiesResponse.getCookie(x);
                System.out.println("Key = " + x + ": value = " + cookieValue);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}