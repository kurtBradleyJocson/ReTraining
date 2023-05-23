package rest_assured_examples_master;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class JsonSchemaValidationTest {
	
	
	String URL = "https://reqres.in/api/booking";
	
	@Test
	public void testCreateBookingJsonSchema() {
		
        Response response = RestAssured.given()
                .get(URL);

            String jsonResponse = response.getBody().asString();
            System.out.println(jsonResponse);
		
	}
	
	@Test
    public void testUpdatePartialBookingJsonSchema () {
		
		String requestBody = "{\"firstname\":\"" + "Bradley" + "\"," + "\"lastname\":\"" + "Jocson" + "\","
				+ "\"totalprice\":300," + "\"depositpaid\":true," + "\"additionalneeds\":\"breakfast\","
				+ "\"bookingdates\":{\"checkin\":\"2017-04-16\",\"checkout\":\"2018-02-03\"}}";

		Response response = RestAssured.given().contentType("application/json").body(requestBody)
				.post("https://reqres.in/api/booking/4361");
    }
	
	
    @Test
    public void testCreateJsonSchema () {
    	String requestBody = "{\"firstname\":\"" + "Kurt" + "\"," + "\"lastname\":\"" + "Bradley" + "\","
    			+ "\"totalprice\":300," + "\"depositpaid\":true," + "\"additionalneeds\":\"breakfast\","
    			+ "\"bookingdates\":{\"checkin\":\"2017-04-16\",\"checkout\":\"2018-02-03\"}}";

    	Response response = RestAssured.given().contentType("application/json").body(requestBody)
    			.post("https://restful-booker.herokuapp.com/booking");
    }

}
