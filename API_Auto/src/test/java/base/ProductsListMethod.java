package base;

import endPoint.EndPoint;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;

import static base.ServiceUrl.getUrl;
import static base.ServiceUrl.giveUrl;

public class ProductsListMethod extends EndPoint {

    public  Response getResponce() throws IOException {
        Response response = RestAssured.get(getUrl()).then().extract().response();
        return response;
    }

    // Post request hit and return the response
    public Response postResponse() throws IOException {
        Response response = RestAssured.post(giveUrl()).then().extract().response();
        return response;
    }
}
