package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static base.FinalServiceUrl.*;

public class CrudOperation  {
    public static String url = getFinalUrl();

    //Get Request hit and return the response
    public Response getResponse() {
        Response response = RestAssured.get(url).then().extract().response();
        return response;
    }

    // Post request hit and return the response
    public Response postRequest() {
        Response response = RestAssured.post(url).then().extract().response();
        return response;
    }
}