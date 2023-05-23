package rest_assured_examples_master;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.internal.support.FileReader;


public class MultiPartFileUploadTest {

    private static final String URL = "http://postman-echo.com/post";

    @Test

    public void testFileUpload () {

        String fileName = "calc.csv";
        FileReader fileReader = new FileReader ();

        RestAssured.given().when ()
            .log ()
            .all ()
            .contentType ("multipart/form-data")
            //fileToUpload deprecated .multiPart (fileReader.(fileName))
            .when ()
            .post (URL)
            .then ()
            .statusCode (200)
            .log ()
            .all ()
            .and ()
            .assertThat ()
            .body ("files", null);
        
        Assert.assertNotNull(fileReader);

    }
}
