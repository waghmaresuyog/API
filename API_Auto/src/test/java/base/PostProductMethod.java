package base;


import endPoint.EndPoint;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;

import static base.Base_URL.giveUrl;


public class PostProductMethod extends EndPoint {

    // Post request hit and return the response
    public Response postResponse() throws IOException {
        Response response = RestAssured.post(giveUrl()).then().extract().response();
        return response;
    }
}
