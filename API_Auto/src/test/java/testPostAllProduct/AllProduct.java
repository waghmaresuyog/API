package testPostAllProduct;

import base.PostProductMethod;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class AllProduct extends PostProductMethod {
    private static final Logger log = LogManager.getLogger("AllProduct.class");

    //check status code hitting the post request
    @Test(priority = 1)
    public void checkStatusCode() throws IOException {
        Assert.assertEquals(postResponse().getStatusCode(), 200);
        log.info("The response code is : " + postResponse().getStatusCode());
    }

    @Test(priority = 2)
    public void checkResponse() throws IOException {
        JsonPath jsonResponse = new JsonPath(postResponse().asString());
        ResponseBody body = postResponse().getBody(); //use to print response body
        log.info("Response Body is: " + body.asString());
        log.info("the response message is : " + jsonResponse.get("message"));
        Assert.assertEquals(jsonResponse.get("message"), "This request method is not supported.");
    }
}