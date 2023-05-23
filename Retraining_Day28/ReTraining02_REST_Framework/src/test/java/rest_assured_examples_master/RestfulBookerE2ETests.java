package rest_assured_examples_master;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class RestfulBookerE2ETests {
	
	@Test
	public void createBookingTest() {
	    // Remove method body
    	String requestBody = "{\"firstname\":\"" + "Kurt" + "\"," + "\"lastname\":\"" + "Bradley" + "\","
    			+ "\"totalprice\":300," + "\"depositpaid\":true," + "\"additionalneeds\":\"breakfast\","
    			+ "\"bookingdates\":{\"checkin\":\"2017-04-16\",\"checkout\":\"2018-02-03\"}}";

    	Response response = RestAssured.given().contentType("application/json").body(requestBody)
    			.post("https://restful-booker.herokuapp.com/booking");
	}

	@Test
	public void getBookingTest() {
	    // Remove method body
    	
    	Response response = RestAssured.given().contentType("application/json")
    			.get("https://restful-booker.herokuapp.com/booking");
	}

	@Test
	public void updateBookingTest() {
	    // Remove method body
		String requestBody = "{\"firstname\":\"" + "Bradley" + "\"," + "\"lastname\":\"" + "Jocson" + "\","
				+ "\"totalprice\":300," + "\"depositpaid\":true," + "\"additionalneeds\":\"breakfast\","
				+ "\"bookingdates\":{\"checkin\":\"2017-04-16\",\"checkout\":\"2018-02-03\"}}";

		Response response = RestAssured.given().contentType("application/json").body(requestBody)
				.post("https://reqres.in/api/booking/4361");
	}

	@Test
	public void updatePartialBookingTest() {
	    // Remove method body
		String requestBody = "{\"firstname\":\"" + "Jocson" + "\"," + "\"lastname\":\"" + "Jocson" + "\","
				+ "\"totalprice\":300," + "\"depositpaid\":true," + "\"additionalneeds\":\"breakfast\","
				+ "\"bookingdates\":{\"checkin\":\"2017-04-16\",\"checkout\":\"2018-02-03\"}}";

		Response response = RestAssured.given().contentType("application/json").body(requestBody)
				.post("https://reqres.in/api/booking/4361");
	}

	@Test
	public void deleteBookingTest() {
	    // Remove method body
		
		Response response = RestAssured.given().contentType("application/json")
				.delete("https://reqres.in/api/booking/");
	}

	@Test
	public void checkBookingIsDeleted() {
	    // Remove method body
		Response response = RestAssured.given().contentType("application/json")
    			.get("https://restful-booker.herokuapp.com/booking");
		
		Assert.assertNull(response);
		
	}
	
	
}
