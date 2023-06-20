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

public class AllProduct extends PostProduct {
    private static final Logger log = LogManager.getLogger("AllProduct.class");

    @Test(priority = 1)
    public void checkStatusCode() throws IOException {
        Response response = RestAssured.post(giveUrl()).then().extract().response();
        Assert.assertEquals(response.getStatusCode(), 200);
        log.info("The response code is : " + response.getStatusCode());
    }

    @Test(priority = 2)
    public void checkResponse() throws IOException {
        Response response = RestAssured.post(giveUrl()).then().extract().response();
        JsonPath jsonResponse = new JsonPath(response.asString());
        ResponseBody body = response.getBody(); //use to print response body
        log.info("Response Body is: " + body.asString());
        log.info("the responce message is : " + jsonResponse.get("message"));
        Assert.assertEquals(jsonResponse.get("message"), "This request method is not supported.");
    }
}
