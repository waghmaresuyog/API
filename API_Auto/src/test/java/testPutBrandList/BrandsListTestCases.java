package testPutBrandList;

import base.CrudOperation;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class BrandsListTestCases extends CrudOperation {

    private static final Logger log = LogManager.getLogger("BrandsListTestCases.class");

    @Test(priority = 1)
    public void checkBrandStatusCode() {
        Assert.assertEquals(putBrandsListResponse().getStatusCode(), 200);
        log.info("Status code is " + putBrandsListResponse().getStatusCode());
    }

    @Test(priority = 2)
    public void responseBrandsPutMethod() {
        JsonPath jsonResponse = new JsonPath(putBrandsListResponse().asString());
        ResponseBody body = postRequest().getBody(); //use to print response body
        log.info("Response Body is: " + body.asString());
        log.info("the response message is : " + jsonResponse.get("message"));
        Assert.assertEquals(jsonResponse.get("message"), "This request method is not supported.");
    }
}
