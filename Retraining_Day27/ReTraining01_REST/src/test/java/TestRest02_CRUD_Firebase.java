import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestRest02_CRUD_Firebase {

	public static void main(String[] args) {

//		post();
//		put();
		delete();
		get();
	}
	
	
	public static void get() {

		RestAssured.given().when()
				.get("https://forrestapilessons-default-rtdb.firebaseio.com/test.json")
				.then().log().all();
	}
	
	
	public static void post() {

		// Define the request body
		String requestBody = "{\"test\":\"testPost\"}";

		// Make the POST request
		Response response = RestAssured.given()
		        .body(requestBody)
		        .post("https://forrestapilessons-default-rtdb.firebaseio.com/test.json");

		// Print the response
		response.then().log().all();
	}
	
	public static void put() {
	    // Define the request body
	    String requestBody = "{\"test\":\"testPut\"}";

	    // Make the PUT request
	    Response response = RestAssured.given()
	            .body(requestBody)
	            .put("https://forrestapilessons-default-rtdb.firebaseio.com/test.json");

	    // Print the response
	    response.then().log().all();
	}
	
	
	public static void delete() {
	    // Make the DELETE request
	    Response response = RestAssured.delete("https://forrestapilessons-default-rtdb.firebaseio.com/test.json");

	    // Print the response
	    response.then().log().all();
	}
	
	
	

}
