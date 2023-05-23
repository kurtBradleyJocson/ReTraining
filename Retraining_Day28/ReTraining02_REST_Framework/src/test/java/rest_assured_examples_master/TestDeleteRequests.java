package rest_assured_examples_master;

import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class TestDeleteRequests {
	
    private static final String URL = "https://reqres.in/api/users/";
	
	@Test
	public void deleteRequestTests(final int userId) {
		RestAssured.given().when().delete(URL + userId).then().assertThat().statusCode(204);
	}
	
}
