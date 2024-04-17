import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
@Test
public class ApiTestcase {
    @Test
    public static void getcall(){
        Response response= given()
                .contentType(ContentType.JSON)
                .param("user id",1234).baseUri("http://localhost:5555")
                .when().get().then()
                .assertThat()
                .statusCode(200)
                .log().all()
                .extract().response();
        JsonPath jsonPath= response.jsonPath();
        Assert.assertEquals("aneena",jsonPath.getString("username"));
    }
    public static void putcall(){
        Response response= given().contentType(ContentType.JSON).header("Authorization","bearer")
                 .body("{\"usernmae\":\"george\"}")
                .put("http://localhost:5555")
                .then()
                .assertThat().statusCode(200)
                .log().all()
                .extract().response();
        Assert.assertEquals(200, response.statusCode());
        JsonPath jsonPath= response.jsonPath();
        Assert.assertEquals("george",jsonPath.getString("username"));

    }

    public static void postcall(){
        Response response= given().contentType(ContentType.JSON).header("Authorization","bearer")
                .body("{\"usernmae\":\"aneeee\"}")
                .post("http://localhost:5555")
                .then()
                .assertThat().statusCode(201)
                .log().all()
                .extract().response();
        JsonPath jsonPath= response.jsonPath();
        Assert.assertEquals("george",jsonPath.getString("username"));

    }
}
