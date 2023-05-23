package rest_assured_examples_master;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.xml.XmlPath;
import io.restassured.path.xml.config.XmlPathConfig;

public class AddNumbersAPITests {

    private static final String BASE_URL = "http://www.dneonline.com/calculator.asmx";

    @Test
    public void testAddNumbers () {

        URI file = null;
        try {
            file = getClass ().getClassLoader ()
                .getResource ("addrequest.xml")
                .toURI ();
        } catch (URISyntaxException e) {
            throw new Error ("Error in URI syntax", e);
        }
        File body = new File (file);

        String response = RestAssured.given().when ()
            .body (body)
            .header ("SOAPAction", "http://tempuri.org/Add")
            .contentType ("text/xml; charset=utf-8")
            .log ()
            .all ()
            .post (BASE_URL)
            .then ()
            .log ()
            .all ()
            .statusCode (200)
            .extract ()
            .response ()
            .asString ();

        XmlPath xmlpath = new XmlPath (response).using (new XmlPathConfig ("UTF-8"));
        assertEquals (xmlpath.getString ("AddResult"), "10");

    }
}
