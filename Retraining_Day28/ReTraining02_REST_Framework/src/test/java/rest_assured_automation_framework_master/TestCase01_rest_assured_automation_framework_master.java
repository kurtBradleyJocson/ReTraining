package rest_assured_automation_framework_master;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class TestCase01_rest_assured_automation_framework_master {

	ExtentTest test;
	ExtentReports report;

	@Test(priority = 1)
	public void validLoginTest() {

		report = new ExtentReports(
				"/Users/collabera/eclipse-workspace/ReTraining02_REST_Framework/reports/Reports.html");

		test = report.startTest("Test");

		String requestBody = "{\"username\":\"admin\",\"password\":\"password123\"}";

		Response response = RestAssured.given().contentType("application/json").body(requestBody)
				.post("https://restful-booker.herokuapp.com/auth");

		String authToken = response.jsonPath().getString("token");

		int statusCode = response.getStatusCode();

		try {

			test.log(LogStatus.INFO, "URL is: https://restful-booker.herokuapp.com/auth");
			Assert.assertEquals(200, statusCode);
			test.log(LogStatus.PASS, "Asserting response code");

			boolean testEmpty = authToken.length() > 0 ? false : true;
			Assert.assertEquals(testEmpty, false);
			test.log(LogStatus.PASS, "Asserting response value not empty case");

			Assert.assertNotNull(authToken);
			test.log(LogStatus.PASS, "Asserting response value not null case");

		} catch (AssertionError e) {

			test.log(LogStatus.FAIL, "Assertion Failure: " + e.getMessage());
		}

	}

	@Test(priority = 2)
	public void invalidLoginTest() {

		String requestBody = "{\"username\":\"dummy\",\"password\":\"dummypassword123\"}";

		Response response = RestAssured.given().contentType("application/json").body(requestBody)
				.post("https://restful-booker.herokuapp.com/auth");

		String authToken = response.jsonPath().getString("token");
		String theReason = response.jsonPath().getString("reason");

		int statusCode = response.getStatusCode();

		try {

			test.log(LogStatus.INFO, "URL is: https://restful-booker.herokuapp.com/auth");
			Assert.assertEquals(200, statusCode);
			test.log(LogStatus.INFO, "Asserting response code");

			boolean testReason = theReason.equals("Bad credentials") ? true : false;
			Assert.assertEquals(testReason, true);
			test.log(LogStatus.INFO, "Asserting response value == Bad credentials");

		} catch (AssertionError e) {
			test.log(LogStatus.FAIL, "Assertion Failure: " + e.getMessage());
		}

	}

	@AfterTest
	public void endTest() {

		report.endTest(test);
		report.flush();
	}

}
