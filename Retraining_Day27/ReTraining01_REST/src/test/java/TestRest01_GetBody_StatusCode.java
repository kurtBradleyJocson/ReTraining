import io.restassured.RestAssured;

public class TestRest01_GetBody_StatusCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getResponseBody1();
		getResponseBody2();
		getStatusCode();
	}
	
	
	public static void getResponseBody1() {

		System.out.println("RESPONS1");

		RestAssured.given().when()
				.get("http://demo.guru99.com/V4/sinkministatement.php?CUSTOMER_ID=68195&PASSWORD=1234!&Account_No=1")
				.then().log().all();
	}

	public static void getResponseBody2() {
		System.out.println("RESPONS2");

		RestAssured.given().queryParam("CUSTOMER_ID", "68195").queryParam("PASSWORD", "1234!").queryParam("Account_No", "1").when()
				.get("http://demo.guru99.com/V4/sinkministatement.php").then().log().all();
	}
	
	
	public static void getStatusCode() {
		System.out.println("Status Code");

		int stats = RestAssured.given().queryParam("CUSTOMER_ID", "68195").queryParam("PASSWORD", "1234!").queryParam("Account_No", "1").when()
				.get("http://demo.guru99.com/V4/sinkministatement.php").getStatusCode();
		
		RestAssured.given().queryParam("CUSTOMER_ID", "68195").queryParam("PASSWORD", "1234!").queryParam("Account_No", "1").when()
		.get("http://demo.guru99.com/V4/sinkministatement.php").then().assertThat().statusCode(200);
		
		System.out.println(stats);
		
	}

}
