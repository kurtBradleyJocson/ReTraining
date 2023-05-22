package testng_testcases_rewritten;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Testcase01_fullTestCase {
	
	@Test(priority = 1)
	public void getAuthToken() {

		System.out.println("Get Auth Token");

		String requestBody = "{\"username\":\"admin\",\"password\":\"password123\"}";

		Response response = RestAssured.given().contentType("application/json").body(requestBody)
				.post("https://restful-booker.herokuapp.com/auth");

		String authToken = response.jsonPath().getString("token");

		System.out.println(authToken);

	}

	int id;

	@Test(priority = 2)
	public void postBooking() {
		System.out.println("\n\n=======================================");
		System.out.println("Post Done!\n\n");

		String requestBody = "{\"firstname\":\"" + "Kurt" + "\"," + "\"lastname\":\"" + "Bradley" + "\","
				+ "\"totalprice\":300," + "\"depositpaid\":true," + "\"additionalneeds\":\"breakfast\","
				+ "\"bookingdates\":{\"checkin\":\"2017-04-16\",\"checkout\":\"2018-02-03\"}}";

		Response response = RestAssured.given().contentType("application/json").body(requestBody)
				.post("https://restful-booker.herokuapp.com/booking");

		id = response.then().extract().path("bookingid");

		System.out.println("Booking ID: " + response.then().extract().path("bookingid"));

	}
	
	public void get() {
		RestAssured.given().when()
		.get("https://restful-booker.herokuapp.com/booking/"+id)
		.then().log().all();
	}
	
	@Test(priority = 3)
	public void getBooking() {
		
		System.out.println("\n\n=======================================");
		System.out.println("Get Current\n\n");

		get();

	}
	
	@Test(priority = 4)
	public void partialUpdateBooking() {
		
		System.out.println("\n\n=======================================");
		System.out.println("Partial Update Done!\n\n");
		
		String requestBody = "{\"firstname\":\"" + "Bradley" + "\"," + "\"lastname\":\"" + "Jocson" + "\","
				+ "\"totalprice\":300," + "\"depositpaid\":true," + "\"additionalneeds\":\"breakfast\","
				+ "\"bookingdates\":{\"checkin\":\"2017-04-16\",\"checkout\":\"2018-02-03\"}}";

		Response response = RestAssured.given().contentType("application/json").body(requestBody)
				.post("https://restful-booker.herokuapp.com/booking/"+id);
		
		System.out.println("\n\n=======================================");
		System.out.println("Get Partial Update\n\n");
		
		get();

	}
	
	
	@Test(priority = 5)
	public void updateBooking() {
		
		System.out.println("\n\n=======================================");
		System.out.println("Update Booking Done!\n\n");
		
		String requestBody = "{\"firstname\":\"" + "John" + "\"," + "\"lastname\":\"" + "Doe" + "\","
				+ "\"totalprice\":500," + "\"depositpaid\":false," + "\"additionalneeds\":\"lunch\","
				+ "\"bookingdates\":{\"checkin\":\"2022-01-01\",\"checkout\":\"2022-01-10\"}}";

		Response response = RestAssured.given().contentType("application/json").body(requestBody)
				.post("https://restful-booker.herokuapp.com/booking/"+id);
		
		System.out.println("\n\n=======================================");
		System.out.println("Get Updated Booking\n\n");
		
		get();

	}
	

	@Test(priority = 6)
	public void f() {
		
		
		System.out.println("\n\n=======================================");
		System.out.println("Before Delete\n\n");
		
		get();

		Response response = RestAssured.given().contentType("application/json")
				.delete("https://restful-booker.herokuapp.com/booking/" + id);
		
		
		System.out.println("\n\n=======================================");
		System.out.println("After Delete Done!\n\n");
		
		get();

	}

}
