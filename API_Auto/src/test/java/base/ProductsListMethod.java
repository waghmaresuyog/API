package base;


import endPoint.EndPoint;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;

import static base.ServiceUrl.productBaseUrl;


public class PostProductMethod extends EndPoint {

    public static String finalUrl;

    // combine base url and end point store into finalUrl and return it
    public static String giveUrl() throws IOException {
        finalUrl = productBaseUrl() + productEndURL();
        return finalUrl;
    }

    // Post request hit and return the response
    public Response postResponse() throws IOException {
        Response response = RestAssured.post(giveUrl()).then().extract().response();
        return response;
    }
}
