package postAllProduct;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AllProduct {
    private static String url;

    public AllProduct() {
        try {
            url = Reader.getUrl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Logger log = LogManager.getLogger("AllProduct.class");

    @Test(priority = 1)
    public void checkStatusCode() {
        Response response = RestAssured.post(url).then().extract().response();
        Assert.assertEquals(response.getStatusCode(), 200);
        log.info("The response code is : " + response.getStatusCode());
    }

    @Test(priority = 2)
    public void checkResponse() {
        Response response = RestAssured.post(url).then().extract().response();
        JsonPath jsonResponse = new JsonPath(response.asString());
        ResponseBody body = response.getBody(); //use to print response body
        log.info("Response Body is: " + body.asString());
        Assert.assertEquals(response.getStatusCode(), 200);
        log.info("the Status code is : " + response.getStatusCode());
    }
}