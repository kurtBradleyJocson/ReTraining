package rest_assured_examples_master;

import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.authentication.BasicAuthScheme;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static org.hamcrest.Matchers.*;

import java.io.IOException;
import java.net.http.HttpRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpException;
import org.apache.http.HttpMessage;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HttpContext;
import org.hamcrest.Matchers;

public class AuthenticationTests {
	
	private static RequestSpecBuilder requestSpecBuilder;
	private static ResponseSpecBuilder responseSpecBuilder;
	private static ResponseSpecification responseSpecification;
	private static RequestSpecification requestSpecification;
	
    @BeforeClass
    public void setupSpecBuilder () {
        requestSpecBuilder = new RequestSpecBuilder ().addFilter (new RequestLoggingFilter ())
            .addFilter (new ResponseLoggingFilter ());
        responseSpecBuilder = new ResponseSpecBuilder ().expectStatusCode (200);

        responseSpecification = responseSpecBuilder.build ();
        requestSpecification = requestSpecBuilder.build ();

    }

	@Test
	public void testAPIKeyAuthentication() {

		int id = 2172797;

		RestAssured.given().spec(requestSpecification).queryParam("apiKey", "e4c66f5be087d70d2ba00f3c84a067a1")
				.queryParam("id", id).get("https://api.openweathermap.org/data/2.5/weather").then()
				.spec(responseSpecification).body("id", equalTo(id));
	}

	@Test
	public void testBasicAuth() {
		RestAssured.given().spec(requestSpecification).auth().basic("postman", "password")
				.get("https://postman-echo.com/basic-auth").then().spec(responseSpecification)
				.body("$", hasKey("authenticated")).body("authenticated", equalTo(true));
	}

	@Test
	public void testPreemptiveAuth() {
		
		CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
		credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials("postman", "password"));

		HttpClientBuilder httpClientBuilder = HttpClients.custom()
		        .setDefaultCredentialsProvider(credentialsProvider)
		        .addInterceptorFirst(new HttpRequestInterceptor() {
		            public void process(HttpRequest request, HttpContext context) throws HttpException, IOException {
		                ((HttpMessage) request).addHeader("Authorization", "Basic " + Base64.encodeBase64String("postman:password".getBytes()));
		            }

					public void process(org.apache.http.HttpRequest request, HttpContext context)
							throws HttpException, IOException {
						// TODO Auto-generated method stub
						
					}
		        });

		CloseableHttpClient httpClient = httpClientBuilder.build();

		RestAssured.given().spec(requestSpecification).get("https://postman-echo.com/basic-auth")
				.then().spec(responseSpecification).body("$", hasKey("authenticated"))
				.body("authenticated", equalTo(true));
	}

	@Test
	public void testDigestAuth() {
		RestAssured.given()
	    .spec(requestSpecification)
	    .auth().preemptive().basic("postman", "password")
	    .get("https://postman-echo.com/basic-auth")
	    .then()
	    .spec(responseSpecification)
	    .body("$", hasKey("authenticated"))
	    .body("authenticated", equalTo(true));
	}

}
