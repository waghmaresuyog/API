package client;

import endPoint.ServiceEndPointUrl;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static endPoint.ServiceUrl.*;

public class ProductsListMethod extends ServiceEndPointUrl {

    //Get Request hit and return the response
    public Response getResponse() {
        Response response = RestAssured.get(url()).then().extract().response();
        return response;
    }

    // Post request hit and return the response
    public Response postRequest() {
        Response response = RestAssured.post(url()).then().extract().response();
        return response;
    }
}
