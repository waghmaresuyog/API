package client;

import endPoint.ServiceEndPoint;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;

import static endPoint.ServiceUrl.getUrl;
import static endPoint.ServiceUrl.giveUrl;

public class ProductsListMethod extends ServiceEndPoint {

    public Response getResponse() {
        Response response = RestAssured.get(getUrl()).then().extract().response();
        return response;
    }

    // Post request hit and return the response
    public Response postRequest() {
        Response response = RestAssured.post(giveUrl()).then().extract().response();
        return response;
    }
}
