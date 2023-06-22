package APIAssignments;

import config.UrlReader;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;


public class Items {
    private static String url;

    public Items() {
        try {
            url = UrlReader.getUrl();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static final Logger log = LogManager.getLogger("Items.class");


    @Test(priority = 1)
    public void checkStatusCode() {
        Response response = RestAssured.get(url).then().extract().response();
        Assert.assertEquals(response.getStatusCode(), 200);
        log.info("Status code is " + response.getStatusCode());
    }

    @Test(priority = 2)
    public void checkProductList() {
        var getList = given().when().get(url).then().log().all().toString();
        log.info("Product List is " + getList);
    }

    @Test(priority = 3)
    public void checkContent() {
        Response response = RestAssured.get(url).then().extract().response();
        JsonPath jsonObject = new JsonPath(response.asString());//response convert json into string
        List<Product> productsArray = jsonObject.get("products");
        List<Product> getProductData = new ArrayList<>();
        Product objectProduct = new Product();
        objectProduct.setProductId(String.valueOf(1));
        objectProduct.setProductName("Blue Top");
        objectProduct.setProductPrice("Rs. 500");
        objectProduct.setProductBrand("Polo");
        getProductData.add(objectProduct);
        for (int index = 0; index < productsArray.size(); index++) {
            String id = jsonObject.getString("products[" + index + "].id");
            String name = jsonObject.getString("products[" + index + "].name");
            String price = jsonObject.getString("products[" + index + "].price");
            String brand = jsonObject.getString("products[" + index + "].brand");
            getProductData.forEach(displayList -> {
                if (name.equals(objectProduct.getProductName())) {
                    log.info(objectProduct.getProductId());
                    log.info(objectProduct.getProductName());
                    log.info(objectProduct.getProductBrand());
                    log.info(objectProduct.getProductPrice());
                    Assert.assertEquals(objectProduct.getProductId(), id);
                    Assert.assertEquals(objectProduct.getProductName(), name);
                    Assert.assertEquals(objectProduct.getProductPrice(), price);
                    Assert.assertEquals(objectProduct.getProductBrand(), brand);
                }
            });
        }
    }

    @Test(priority = 4)
    public void checkLength() {
        var response = given().when().get(url).then().extract().asString();
        JsonPath jsonResponse = new JsonPath(response);
        var idLength = jsonResponse.getInt("products.id.size()");
        log.info("The length is : " + idLength + " products");
        Assert.assertEquals(idLength, 34, "number of products are Not expected");
    }
}