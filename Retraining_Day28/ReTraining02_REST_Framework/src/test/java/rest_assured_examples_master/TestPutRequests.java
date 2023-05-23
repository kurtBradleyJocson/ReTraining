package rest_assured_examples_master;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestPutRequests {
	private static final String URL = "https://reqres.in";

	@Test
	public void postBooking() {


		String requestBody = "{\"firstname\":\"" + "Kurt" + "\"," + "\"lastname\":\"" + "Bradley" + "\","
				+ "\"totalprice\":300," + "\"depositpaid\":true," + "\"additionalneeds\":\"breakfast\","
				+ "\"bookingdates\":{\"checkin\":\"2017-04-16\",\"checkout\":\"2018-02-03\"}}";

		Response response = RestAssured.given().contentType("application/json").body(requestBody)
				.post(URL);

		

	}
}
