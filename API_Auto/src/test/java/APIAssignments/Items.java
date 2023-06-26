package APIAssignments;

import base.ProductsListMethod;
import io.restassured.path.json.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.*;

public class Items extends ProductsListMethod {

    private static final Logger log = LogManager.getLogger("Items.class");

    @Test(priority = 1)
    public void checkStatusCode() throws IOException {
        Assert.assertEquals(getResponce().getStatusCode(), 200);
        log.info("Status code is " + getResponce().getStatusCode());
    }

    @Test(priority = 2)
    public void checkProductList() throws IOException {
        var getList = getResponce().asString();
        log.info("Product List is " + getList);
    }

    @Test(priority = 3)
    public void checkContent() throws IOException {
        //response convert json into string
        JsonPath jsonObject = new JsonPath(getResponce().asString());
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
    public void checkLength() throws IOException {
        var response = getResponce().asString();
        JsonPath jsonResponse = new JsonPath(response);
        var idLength = jsonResponse.getInt("products.id.size()");
        log.info("The length is : " + idLength + " products");
        Assert.assertEquals(idLength, 34, "number of products are Not expected");
    }
}