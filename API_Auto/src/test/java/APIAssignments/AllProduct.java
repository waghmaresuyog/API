package APIAssignments;

import org.testng.annotations.Test;

public class AllProduct {

    @Test(priority = 1)
    public void checkStatusCode()
    {
        RestAssured.baseURI="https://automationexercise.com/api/productsList";
        Response response = RestAssured.post("https://automationexercise.com/api/productsList").then().extract().response();
        Assert.assertEquals(response.getStatusCode(), 200);
        System.out.println("The response code is : " + response.getStatusCode());

    }
}
