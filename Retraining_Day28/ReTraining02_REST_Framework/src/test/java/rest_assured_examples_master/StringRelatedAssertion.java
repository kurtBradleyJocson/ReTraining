package rest_assured_examples_master;

import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;

import org.hamcrest.Matchers;

public class StringRelatedAssertion {
	
    private static RequestSpecBuilder requestSpecBuilder;
    private static ResponseSpecBuilder responseSpecBuilder;
    private static ResponseSpecification responseSpecification;
    private static RequestSpecification requestSpecification;

    @BeforeClass
    public static void setup() {
        requestSpecBuilder = new RequestSpecBuilder();
        responseSpecBuilder = new ResponseSpecBuilder();

        requestSpecification = requestSpecBuilder.build();
        responseSpecification = responseSpecBuilder.build();
    }

    @Test(priority = 1)
    public void testNumberAssertions() {
        String URL = "https://reqres.in/api/users/";

        Response response = RestAssured.given()
                .queryParam("page", 2)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get(URL);

        response.then()
                .spec(responseSpecification)
                .body("page", equalTo(2));
    }
    
    @Test(priority = 2)
    public void testStringAssertions() {
        String URL = "https://reqres.in/api/users/";

        Response response = RestAssured.given()
                .queryParam("page", 2)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get(URL);

        response.then()
                .assertThat()
                .body("data[0].first_name", Matchers.equalTo("Michael"))
                .body("data[0].first_name", Matchers.equalToIgnoringCase("MICHael"))
                .body("data[0].email", Matchers.containsString("michael.lawson"))
                .body("data[0].last_name", Matchers.startsWith("L"))
                .body("data[0].last_name", Matchers.endsWith("n"))
                .body("data[1].first_name", Matchers.equalTo("Lindsay".trim()));
    }
    
    @Test
    public void testNotNullAssertions() {
        String URL = "https://reqres.in/api/users/";

        RestAssured.given()
                .spec(requestSpecification)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get(URL)
                .then()
                .spec(responseSpecification)
                .assertThat()
                .body("data[0].first_name", Matchers.notNullValue());
    }
    
    @Test
    public void testHasKeyAssertion() {
        String URL = "https://reqres.in/api/users/";

        RestAssured.given()
                .spec(requestSpecification)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get(URL)
                .then()
                .spec(responseSpecification)
                .assertThat()
                .body("data[0]", Matchers.hasKey("email"))
                .body("support", Matchers.hasKey("url"))
                .body("$", Matchers.hasKey("page"))
                .body("$", Matchers.hasKey("total"));
    }
    

    @Test
    public void testNotAssertions() {
        String URL = "https://reqres.in/api/users/";

        RestAssured.given()
                .spec(requestSpecification)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get(URL)
                .then()
                .spec(responseSpecification)
                .assertThat()
                .body("data", Matchers.not(Matchers.empty()))
                .body("data[0].first_name", Matchers.not(Matchers.equalTo("George")))
                .body("data.size()", Matchers.greaterThan(5));
    }
    
    @Test
    public void testMultipleAssertStatement() {
        String URL = "https://reqres.in/api/users/";

        RestAssured.given()
                .spec(requestSpecification)
                .filter(new RequestLoggingFilter())
                .filter(new ResponseLoggingFilter())
                .get(URL)
                .then()
                .spec(responseSpecification)
                .assertThat()
                .body("page", Matchers.equalTo(2))
                .body("data[0].first_name", Matchers.equalTo("Michael"))
                .body("support.url", Matchers.notNullValue());
    }



}
