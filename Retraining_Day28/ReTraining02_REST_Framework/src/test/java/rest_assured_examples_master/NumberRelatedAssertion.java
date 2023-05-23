package rest_assured_examples_master;

import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static org.hamcrest.Matchers.*;

public class NumberRelatedAssertion {
	
	@Test(priority=1)
	public void testNumberAssertions() {
		
		String URL = "https://reqres.in/api/users/";

		Response response = RestAssured.given()
		        .queryParam("page", 2)
		        .get(URL);

		response.then()
		        .statusCode(200)
		        .assertThat()
		        .body("page", equalTo(2))
		        .body("per_page", greaterThan(4))
		        .body("per_page", greaterThanOrEqualTo(6))
		        .body("total", lessThan(14))
		        .body("total_pages", lessThanOrEqualTo(3));

	}
	
	@Test(priority=1)
	public void testGreaterThanAssertions() {
		
		String URL = "https://reqres.in/api/users/";

		Response response = RestAssured.given()
		        .queryParam("page", 2)
		        .get(URL);
		
		response.then()
		        .statusCode(200)
		        .assertThat()
		        .body("page", equalTo(2))
		        .body("per_page", greaterThan(4))
		        .body("per_page", greaterThanOrEqualTo(6));

	}
	
	@Test(priority=3)
	public void testLessThanAssertions() {
		
		String URL = "https://reqres.in/api/users/";

		Response response = RestAssured.given()
		        .queryParam("page", 2)
		        .get(URL);
		
		response.then()
		        .statusCode(200)
		        .assertThat()
		        .body("page", equalTo(2))
	            .body ("total", lessThan (14))
	            .body ("total_pages", lessThanOrEqualTo (3));

	}
	
	
	
}
