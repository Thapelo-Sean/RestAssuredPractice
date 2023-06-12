package Day1;

import org.testng.annotations.Test;
import java.util.HashMap;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class httpRequests {

    //CRUD Implementation --> Create, Read, Update, Delete

    int id;

    //get list of users
    @Test(priority = 1)
    void getUsers()
    {
        given()
                .when()
                    .get("https://reqres.in/api/users?page=2")
                .then()
                    .body("page", equalTo(2))
                    .statusCode(200)
                    .log().all();
    }

    //get single user
    @Test
    void getSingleUser()
    {
        given()
                .when()
                    .get("https://reqres.in/api/users/3")
                .then()
                    .statusCode(200)
                    .log().all();
    }

    //create a user
    @Test(priority = 2)
    void createUser()
    {
        HashMap hm = new HashMap();
        hm.put("name", "Thapelo MATJI");
        hm.put("job", "Software Tester");

         id = given()
                    .contentType("application/json")
                    .body(hm)
                .when()
                    .post("https://reqres.in/api/users")
                    .jsonPath().getInt("id");
    }

    //update user
    @Test(priority = 3,dependsOnMethods = ("createUser"))
    void updateUser()
    {
        HashMap hm = new HashMap();
        hm.put("name", "Shawn MATJI");
        hm.put("job", "Automation Engineer");

        given()
                    .contentType("application/json")
                    .body(hm)
                .when()
                    .put("https://reqres.in/api/users/"+ id)
                .then()
                    .statusCode(200)
                    .log().all();
    }

    //delete a user
    @Test
    void deleteUser()
    {
        given()
                .when()
                    .delete("https://reqres.in/api/users/"+ id)
                .then()
                    .statusCode(204)
                    .log().all();
    }
}