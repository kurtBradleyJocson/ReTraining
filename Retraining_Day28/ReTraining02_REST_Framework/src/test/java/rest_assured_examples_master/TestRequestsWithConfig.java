package rest_assured_examples_master;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;



public class TestRequestsWithConfig {

	private static final String URL = "https://apimocha.com/checkt/rover";

    @Test
    public void getRequestTest (final int userId)  {
        Response response = (Response) RestAssured.given().when()
            .get (URL + userId)
            .then ()
            .statusCode(200);

        final int statusCode = RestAssured.given().when ()
            .get (URL + userId)
            .statusCode ();


        final String responseBody = RestAssured.given().when ()
            .get (URL + userId)
            .getBody ()
            .asString ();
        
        Assert.assertEquals(responseBody, "dog");

    }
    
}
