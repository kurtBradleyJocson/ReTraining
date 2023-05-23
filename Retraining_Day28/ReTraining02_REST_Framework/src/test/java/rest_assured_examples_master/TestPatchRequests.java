package rest_assured_examples_master;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestPatchRequests {

	private static final String URL = "https://reqres.in";

	@Test
	public void patchRequestTests () {


		String requestBody = "{\"firstname\":\"" + "Kurt" + "\"," + "\"lastname\":\"" + "Bradley" + "\","
				+ "\"totalprice\":300," + "\"depositpaid\":true," + "\"additionalneeds\":\"breakfast\","
				+ "\"bookingdates\":{\"checkin\":\"2017-04-16\",\"checkout\":\"2018-02-03\"}}";

		Response response = RestAssured.given().contentType("application/json").body(requestBody)
				.patch(URL);

		

	}
	
	
}
