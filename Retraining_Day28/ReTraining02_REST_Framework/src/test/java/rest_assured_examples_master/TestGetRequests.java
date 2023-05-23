package rest_assured_examples_master;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;

public class TestGetRequests {

	private static final String URL = "https://reqres.in/api/users/";


    @Test
    public void getRequestTest (final int userId)  {
        RestAssured.given().when ()
            .get (URL + userId)
            .then ()
            .statusCode(200)
            .and ()
            .assertThat();

        final int statusCode = RestAssured.given().when ()
            .get (URL + userId)
            .statusCode ();


        final String responseBody = RestAssured.given().when ()
            .get (URL + userId)
            .getBody ()
            .asString ();

    }
    
    @Test (dataProvider = "getUserData")
    public void getRequestTestWithQueryParam (final int userPage) throws JSONException {
        RestAssured.given().when ()
            .queryParam ("page", userPage)
            .get (URL)
            .then ()
            .statusCode (200);

        final String responseBody = RestAssured.given().when ()
            .queryParam ("page", userPage)
            .get (URL)
            .getBody ()
            .asString ();

        JSONObject jsonObject = new JSONObject (responseBody);
        JSONArray dataArray = jsonObject.getJSONArray ("data");
        JSONObject dataObject = dataArray.getJSONObject (0);
        String first_name = dataObject.get ("first_name")
            .toString ();

    }
}
