package rest_assured_examples_master;

import org.testng.annotations.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BaseSetup {
    @BeforeClass
    public void setup () {

        RequestSpecification requestSpecification = new RequestSpecBuilder ().setBaseUri (
                "http://localhost:3001")
            .addHeader ("Content-Type", "application/json")
            .addHeader ("Accept", "application/json")
            .addFilter (new RequestLoggingFilter ())
            .addFilter (new ResponseLoggingFilter ())
            .build ();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder().build();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;

    }
}
