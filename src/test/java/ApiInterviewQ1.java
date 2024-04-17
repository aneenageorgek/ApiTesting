import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

@Test
public class ApiInterviewQ1 {
    public static void getCall(){
       Response response= given().contentType(ContentType.JSON).baseUri("https://google.com")
               .when().get().then().assertThat().statusCode(200).log().body().extract().response();
       JsonPath jsonPath=response.jsonPath();
       Assert.assertEquals("aneena",jsonPath.getString("username"));
       Assert.assertEquals("200",response.statusCode());
       System.out.println("Response body is"+ response.getBody().asString());
    }

    public static void post() {
        Response response = given().contentType(ContentType.JSON).header("Authorization", "bearer")
                .body("{\"usernmae\":\"aneena\"}")
                .when().post("https:\\google.com").
                        then().assertThat().statusCode(200).log().body().extract().response();
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals("aneena", jsonPath.getString("username"));
    }

    public

}
