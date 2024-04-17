import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.internal.common.assertion.Assertion;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
@Test
public class GetApi {

    //    public static void main(String[] args) {
//        getRequest();
//        getbody();
//    }

       @BeforeMethod
    public static void restGetCall(){
        RestAssured.baseURI="https://www.google.com";

    }

    @Test
    public static void getRequest() {
//one way to check respose code
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then()
                .extract().response();
        Assert.assertEquals(200, response.statusCode());

        //seconde way to check response code
        given().when().get().then().assertThat().statusCode(200);
        System.out.println("The response status is "+response.statusCode());
        System.out.println("The response body is "+response.getBody().asString());

        //check body elements
        given().when().get().then().assertThat()
                .statusCode(200)
                .extract().path("id");

        //Assert.assertEquals("qui est esse", response.jsonPath().getString("title[1]"));
    }

    @Test
    public static void getbody() {

        given()
                .contentType(ContentType.JSON)
                .param("id", "123")
                .when()
                .get()
                .then()
                .log().body();
        //.all()


    }

}
