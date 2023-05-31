package APIAssignments;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

public class AllProduct {
    private static final Logger log = LogManager.getLogger("AllProduct.class");
    @Test(priority = 1)
    public void checkStatusCode() {
        RestAssured.baseURI = "https://automationexercise.com/api/productsList";
        Response response = RestAssured.post("https://automationexercise.com/api/productsList")
                .then().extract().response();
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("The response code is : " + response.getStatusCode());
        log.info("The response code is : "+response.getStatusCode());
    }

    @Test(priority = 2)
    public void checkResponse() {
        Response response = RestAssured.post("https://automationexercise.com/api/productsList")
                .then().extract().response();
        JsonPath jsonResponse = new JsonPath(response.asString());
        ResponseBody body = response.getBody(); //use to print response body
        System.out.println("Response Body is: " + body.asString());//print as string
        System.out.println("The response message is : " + jsonResponse.get("message"));
        System.out.println("The response code is : " + jsonResponse.get("responseCode"));
    }
}
