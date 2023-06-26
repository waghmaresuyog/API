package testGetProductList;

import client.ProductsListMethod;
import io.restassured.path.json.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import payload.Product;

import java.util.ArrayList;
import java.util.List;

public class ItemsTestCases extends ProductsListMethod {

    private static final Logger log = LogManager.getLogger("Items.class");

    @Test(priority = 1)
    public void checkStatusCode() {
        Assert.assertEquals(getResponse().getStatusCode(), 200);
        log.info("Status code is " + getResponse().getStatusCode());
    }

    @Test(priority = 2)
    public void checkProductList() {
        var getList = getResponse().asString();
        log.info("Product List is " + getList);
    }

    @Test(priority = 3)
    public void checkContent() {
        //response convert json into string
        JsonPath jsonObject = new JsonPath(getResponse().asString());
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
        var response = getResponse().asString();
        JsonPath jsonResponse = new JsonPath(response);
        var idLength = jsonResponse.getInt("products.id.size()");
        log.info("The length is : " + idLength + " products");
        Assert.assertEquals(idLength, 34, "number of products are Not expected");
    }
}