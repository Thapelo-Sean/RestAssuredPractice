package Day2;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class WaysToCreateRequestBody {

/*Ways to create a request body
1. Hashmap
2. Using org.Json
3. Using POJO (plain Old Java Object)
4. Using an external json file*/

    //Request body using hashMap
    @Test
    void postUsingHashMap()
    {
        try
        {
            HashMap hm = new HashMap();

            hm.put("name", "John");
            hm.put("location","Angola");
            hm.put("phone", "0447234793");
            String coursesArr[] = {"Java","Python"};
            hm.put("courses",coursesArr);

            given()
                    .contentType("application/json")
                    .body(hm)
                    .when()
                    .post("https://reqres.in/api/users")
                    .then()
                    .body("name", equalTo("John"))
                    .body("location", equalTo("Angola"))
                    .body("phone", equalTo("0447234793"))
                    .body("courses[0]",equalTo("Java"))
                    .body("courses[1]",equalTo("Python"))
                    .statusCode(201)
                    .log().all();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Request body using JSON Object
    @Test
    void postUsingJsonObject()
    {
        try
        {
            JSONObject data = new JSONObject();

            data.put("name", "Powel");
            data.put("location", "USA");
            data.put("phone", "234567");
            String courseArray[] = {"JavaScript","Ruby"};
            data.put("course", courseArray);

            given()
                    .contentType("application/json")
                    .body(data.toString())
                    .when()
                    .post("https://reqres.in/api/users")
                    .then()
                    .body("name", equalTo("Powel"))
                    .body("location", equalTo("USA"))
                    .body("phone", equalTo("234567"))
                    .statusCode(201)
                    .log().all();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //Request body using POJO
    @Test
    void postUsingPOJO()
    {
        try
        {
            POJO_postRequest data = new POJO_postRequest();

            data.setName("Pravin");
            data.setLocation("India");
            data.setPhone("83893211");
            String courses[] = {"Javascript","Python"};
            data.setCourses(courses);

            given()
                    .contentType("application/json")
                    .body(data)
                    .when()
                    .post("https://reqres.in/api/users")
                    .then()
                    .body("name", equalTo("Pravin"))
                    .body("location", equalTo("India"))
                    .body("phone", equalTo("83893211"))
                    .body("courses[0]", equalTo("Javascript"))
                    .body("courses[1]", equalTo("Python"))
                    .statusCode(201)
                    .log().all();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Request body using external Json file
    @Test
    void postUsingJsonFile() throws FileNotFoundException {

        /*file object
        file reader
        JSON tokener
        JSON Object*/
        try
        {

            File myFile = new File(".//body_JSON");
            FileReader myFileReader = new FileReader(myFile);
            JSONTokener jsonTokener = new JSONTokener(myFileReader);
            JSONObject myData = new JSONObject(jsonTokener);

            given()
                    .contentType("application/json")
                    .body(myData.toString())
                    .when()
                    .post("https://reqres.in/api/users")
                    .then()
                    .body("name", equalTo("Marenskie"))
                    .body("location",equalTo("Germany"))
                    .body("phone",equalTo("668758"))
                    .body("courses[0]",equalTo("C"))
                    .body("courses[1]", equalTo("C++"))
                    .statusCode(201)
                    .log().all();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}