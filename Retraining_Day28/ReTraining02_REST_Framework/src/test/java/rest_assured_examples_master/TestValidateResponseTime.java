package rest_assured_examples_master;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class TestValidateResponseTime {
	
    @Test
    public void testResponseTime () {
        RestAssured.given().when ()
            .queryParam ("page", "2")
            .get ("/api/users")
            .time ();
    }
}
