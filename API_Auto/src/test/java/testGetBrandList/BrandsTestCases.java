package testGetBrandList;

import base.CrudOperation;
import io.restassured.path.json.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import payload.Brand;
import payload.Product;

import java.util.ArrayList;
import java.util.List;

public class BrandsTestCases extends CrudOperation {

    private static final Logger log = LogManager.getLogger("BrandsTestCases.class");

    @Test(priority = 1)
    public void checkBrandStatusCode() {
        Assert.assertEquals(getBrandsListResponse().getStatusCode(), 200);
        log.info("Status code is " + getBrandsListResponse().getStatusCode());
    }

    @Test(priority = 2)
    public void checkBrandContent() {
        //  response convert json into string
        JsonPath jsonObject = new JsonPath(getBrandsListResponse().asString());
        List<Brand> brandsArray = jsonObject.get("brands");
        List<Brand> getBrandlist = new ArrayList<>();
        Brand objectBrand= new Brand();
        objectBrand.setBrandId(String.valueOf(1));
        objectBrand.setBrandName("Polo");
        getBrandlist.add(objectBrand);
        for (int index = 0; index < brandsArray.size(); index++) {
            String id = jsonObject.getString("brands[" + index + "].id");
            String brandName = jsonObject.getString("brands[" + index + "].brand");
            getBrandlist.forEach(brand -> {
                if (brandName.equals(objectBrand.getBrandName())) {
                    log.info(brand.getBrandId() + " " + brand.getBrandName());
                    Assert.assertEquals(objectBrand.getBrandId(), id);
                    Assert.assertEquals(objectBrand.getBrandName(), brandName);
                }
            });
        }
    }

    @Test(priority = 3)
    public void checkBrandLength() {
        var response = getBrandsListResponse().asString();
        JsonPath jsonResponse = new JsonPath(response);
        var idLength = jsonResponse.getInt("brands.id.size()");
        log.info("The length is : " + idLength + " brands");
        Assert.assertEquals(idLength, 34, "number of Brand are Not expected");
    }

}