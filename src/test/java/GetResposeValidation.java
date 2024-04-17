import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
@Test
public class GetResposeValidation {

    public static void getcall(){
        Response response= given().contentType(ContentType.JSON).param("userid",1234).when().get("http://localhost:5555")
                .then()
                .log().body()
                .assertThat().statusCode(200)
                .extract().response();
        //System.out.println("response body is----------------------------"+response.getBody().asString());
        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals("aneena", jsonPath.getString("username"));

        RequestSpecification specification = given().contentType(ContentType.JSON);
        response = given().contentType(ContentType.JSON).param("userid",1234).body("{\"username\":\"george\"}").when().put("http://localhost:5555")
                .then()
                .log().body()
                .assertThat().statusCode(200)
                .extract().response();

        Assert.assertEquals("george", jsonPath.getString("username"));
        //System.out.println(jsonPath);
        // Assert.assertEquals(jsonPath.getString("name"), "description");


    }

}
