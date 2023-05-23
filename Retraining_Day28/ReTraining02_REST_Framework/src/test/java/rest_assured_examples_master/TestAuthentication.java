package rest_assured_examples_master;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestAuthentication {
  @Test
  public void testAuthenticationToken() {
	  
	  String requestBody = "{\"username\":\"admin\",\"password\":\"password123\"}";

		Response response = RestAssured.given().contentType("application/json").body(requestBody)
				.post("https://reqres.in");

		String authToken = response.jsonPath().getString("token");
  }
}
