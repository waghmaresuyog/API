package APIAssignments;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
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
    private String productId;
    private String productName;
    private String productPrice;
    private String productBrand;

    public Items() {
        url = ReusableMethod.getUrl();
    }

    public Items(String productId, String productName, String productPrice, String productBrand) {
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productBrand = productBrand;
    }

    private static final Logger log = Logger.getLogger("Product.class");

    @BeforeTest
    public void getLoggerDisplay() {
        PropertyConfigurator.configure("log4j2.properties");
    }

    @Test(priority = 1)
    public void checkStatusCode() {
        {
            baseURI = ReusableMethod.getUrl();
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
        Response response = RestAssured.get(url).then().extract().response();
        JsonPath jsonObject = new JsonPath(response.asString());
        List<Items> productList = jsonObject.getList("products");
        List<Items> mockData = new ArrayList<>();
        Items productLists = new Items("1", "Blue Top", "Rs. 500", "Polo");
        mockData.add(productLists);
        productLists = new Items("2", "Men Tshirt", "Rs. 400", "H&M");
        for (int count = 0; count < productList.size(); count++) {
            mockData.add(productLists);
            String id = jsonObject.getString("products[" + count + "].id");
            String name = jsonObject.getString("products[" + count + "].name");
            String price = jsonObject.getString("products[" + count + "].price");
            String brand = jsonObject.getString("products[" + count + "].brand");
            mockData.forEach(mockProduct -> {
                if (name.equals(mockProduct.productName)) {
                    log.info(mockProduct.productId);
                    log.info(mockProduct.productName);
                    log.info(mockProduct.productBrand);
                    log.info(mockProduct.productPrice);
                    Assert.assertEquals(mockProduct.productId, id);
                    Assert.assertEquals(mockProduct.productName, name);
                    Assert.assertEquals(mockProduct.productPrice, price);
                    Assert.assertEquals(mockProduct.productBrand, brand);
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