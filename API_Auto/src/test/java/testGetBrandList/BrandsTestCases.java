package testGetBrandList;

import base.CrudOperation;
import io.restassured.path.json.JsonPath;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import payload.Brand;

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
        List<Brand> brandData = new ArrayList<>();
        Brand dataList = new Brand("1", "Polo");
        brandData.add(dataList);
        dataList = new Brand("2", "H&M");
        brandData.add(dataList);
        for (int index = 0; index < 2; index++) {
            String id = jsonObject.getString("brands[" + index + "].id");
            String name = jsonObject.getString("brands[" + index + "].brand");
            brandData.forEach(brand -> {
                if (name.equals(brand.brandName)) {
                    log.info(brand.brandId);
                    log.info(brand.brandName);
                    Assert.assertEquals(brand.brandId, id);
                    Assert.assertEquals(brand.brandName, name);
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