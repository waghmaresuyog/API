package postAllProduct;

import APIAssignments.UrlReader;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class PostProduct extends UrlReader {
    public static String finalUrl;

    public static String giveUrl() throws IOException {
        UrlReader urlReader = new UrlReader();
        String baseUrl = urlReader.base();
        String endPoint = urlReader.postUrl();
        finalUrl = baseUrl+endPoint;
        return finalUrl;
    }

    public Response postResponse () throws IOException {
        Response response = RestAssured.post(giveUrl()).then().extract().response();
        return response;
    }

}
