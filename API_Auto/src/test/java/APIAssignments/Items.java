package APIAssignments;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.apache.log4j.PropertyConfigurator;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static io.restassured.RestAssured.*;

public class Items {

    private static String url;

    public Items() {
        url = ReusableMethod.getUrl();
    }

    private static final Logger log = Logger.getLogger("Items.class");

    @BeforeTest
    public void getLoggerDisplay() {
        PropertyConfigurator.configure("log4j2.properties");
    }

    @Test(priority = 1)
    public void checkStatusCode() {
        {
            Response response = RestAssured.get(ReusableMethod.getUrl()).then().extract().response();
            Assert.assertEquals(response.getStatusCode(), 200);
            log.info("Status code is " + response.getStatusCode());
        }
    }

    @Test(priority = 2)
    public void checkProductList() {
        var getList = given().when().get(url).then().log().all().toString();
        log.info("Product List is " + getList);
    }

    @Test(priority = 3)
    public void checkContent() {
        ReusableMethod reusableMethod = new ReusableMethod();
        Response response = RestAssured.get(url).then().extract().response();
        JsonPath jsonObject = new JsonPath(response.asString());//response convert json into string
        List<Product> productsArray = jsonObject.get("products");
        List<Product> getProductData = new ArrayList<>();
        var productID = reusableMethod.productId;
        var productName = reusableMethod.productName;
        var productPrice = reusableMethod.price;
        var productBand = reusableMethod.brand;

        Product objectProduct = new Product();
        objectProduct.setProductId(String.valueOf(productID));
        objectProduct.setProductName(productName);
        objectProduct.setProductPrice(productPrice);
        objectProduct.setProductBrand(productBand);
        objectProduct.getProductId();
        log.info(objectProduct.getProductId());
        log.info(objectProduct.getProductName());
        log.info(objectProduct.getProductBrand());
        log.info(objectProduct.getProductPrice());

        getProductData.add(objectProduct);
        for (int index = 0; index < productsArray.size(); index++) {
            String id = jsonObject.getString("products[" + index + "].id");
            String name = jsonObject.getString("products[" + index + "].name");
            String price = jsonObject.getString("products[" + index + "].price");
            String brand = jsonObject.getString("products[" + index + "].brand");
            getProductData.forEach(displayList -> {
                if (name.equals(displayList.productName)) {
                    log.info(displayList.productId);
                    log.info(displayList.productName);
                    log.info(displayList.productBrand);
                    log.info(displayList.productPrice);
                    Assert.assertEquals(displayList.productId, id);
                    Assert.assertEquals(displayList.productName, name);
                    Assert.assertEquals(displayList.productPrice, price);
                    Assert.assertEquals(displayList.productBrand, brand);
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