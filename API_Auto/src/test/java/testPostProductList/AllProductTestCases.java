package testPostProductList;

import base.CrudOperation;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AllProductTestCases extends CrudOperation {

    private static final Logger log = LogManager.getLogger("AllProduct.class");

    //check status code hitting the post request
    @Test(priority = 1)
    public void statusCodeProductPostMethod() {
        Assert.assertEquals(postRequest().getStatusCode(), 200);
        log.info("The response code is : " + postRequest().getStatusCode());
    }

    @Test(priority = 2)
    public void responseProductPostMethod() {
        JsonPath jsonResponse = new JsonPath(postRequest().asString());
        ResponseBody body = postRequest().getBody(); //use to print response body
        log.info("Response Body is: " + body.asString());
        log.info("the response message is : " + jsonResponse.get("message"));
        Assert.assertEquals(jsonResponse.get("message"), "This request method is not supported.");
    }
}