package Day5;

import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.List;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class parsingXMLResponse {
    //http://restapi.adequateshop.com/api/Traveler?=page=1

    //Approach 1
    @Test
    void testXmlResponse1()
    {
        given()
                .when()
                    .get("http://restapi.adequateshop.com/api/Traveler?=page=1")
                .then()
                    .statusCode(200)
                    .header("Content-Type","application/xml; charset=utf-8")
                    .body("TravelerinformationResponse.travelers.Travelerinformation.id[0]",equalTo("11133"))
                    .body("TravelerinformationResponse.travelers.Travelerinformation.name[0]",equalTo("Developer"))
                    .log().all();
    }

    //Approach 2
    @Test
    void testXMLResponse2()
    {
        Response xmlResponse = given()
                .when()
                    .get("http://restapi.adequateshop.com/api/Traveler?=page=1");

        Assert.assertEquals(xmlResponse.getStatusCode(),200);
        String myId = xmlResponse.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation.id[1]").toString();
        Assert.assertEquals(myId,"11134");
        String myName = xmlResponse.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation.name[1]").toString();
        Assert.assertEquals(myName,"AS");
    }

    @Test
    void testXMLResponseBody()
    {
        Response xmlResponseBody = given()
                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?=page=1");

        //verify total number of travellers
        XmlPath xmlObject = new XmlPath(xmlResponseBody.asString());

        List<String> travellerNames = xmlObject.getList("TravelerinformationResponse.travelers.Travelerinformation.");
        Assert.assertEquals(travellerNames.size(),10);

        //verify if name is present in the response
        List<String> traveller_Names = xmlObject.getList("TravelerinformationResponse.travelers.Travelerinformation.name");

        boolean status = false;
        for(String travellerName: traveller_Names )
        {
            if(travellerName.equals("Developer"))
            {
                status = true;
                break;
            }
        }
        Assert.assertEquals(status,true);
    }
}
