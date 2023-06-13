package Day5;

import org.testng.annotations.Test;
import java.io.File;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class fileUploadAndDownload
{
    //upload a single file
    @Test
    void singleFileUpload()
    {
        try
        {
            File myFile1 = new File("C://Automation//Test1.txt");

            given()
                    .multiPart("file",myFile1)
                    .contentType("multipart/form-data")
                    .when()
                    .post("http://localhost:8080/uploadFile")
                    .then()
                    .statusCode(200)
                    .body("filename",equalTo(myFile1))
                    .log().all();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Upload multiple files
    @Test
    void multipleFileUpload()
    {
        try
        {
            File myFile2 = new File("C://Automation//Test2.txt");
            File myFile3 = new File("C://Automation//Test3.txt");

            given()
                    .multiPart("files",myFile2)
                    .multiPart("files",myFile3)
                    .contentType("multipart/form-data")
                    .when()
                    .post("http:localhost:8080//uplaodMultiFiles")
                    .then()
                    .statusCode(200)
                    .body("[1].filename",equalTo(myFile2))
                    .body("[2].filename",equalTo(myFile3))
                    .log().all();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}