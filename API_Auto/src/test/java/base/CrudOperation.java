package base;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import static base.FinalServiceUrl.*;

public class CrudOperation {
    public static String url = getFinalUrl();
    public static String brandUrl = getBrandsFinalUrl();

    //Get Request hit and return the response for the Product
    public Response getResponse() {
        Response response = RestAssured.get(url).then().extract().response();
        return response;
    }

    // Post request hit and return the response for product
    public Response postRequest() {
        Response response = RestAssured.post(url).then().extract().response();
        return response;
    }

    //Get request hit and return the response for brand list
    public Response getBrandsListResponse() {
        Response response = RestAssured.get(brandUrl).then().extract().response();
        return response;
    }
}