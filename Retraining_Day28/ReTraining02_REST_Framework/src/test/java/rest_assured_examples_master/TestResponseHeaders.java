package rest_assured_examples_master;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class TestResponseHeaders {
	

	private static final String URL = "https://reqres.in/api/users/";
	
    @Test
    public void responseHeadersTest (final int userId) {
        RestAssured.given().when ()
        .with ()
        .queryParam ("page", 2)
        .get ("/api/users")
        .then ()
        .statusCode (200)
        .and ()
        .assertThat()
        .header ("Content-Type", "application/json; charset=utf-8");
        
        
        
    }
}
