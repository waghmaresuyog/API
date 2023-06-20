package postAllProduct;

import APIAssignments.UrlReader;

import java.io.IOException;

public class PostProduct extends UrlReader {
    public static String finalUrl;
    public static String commonBaseUrl = "https://automationexercise.com/api/";

    public static String giveUrl() throws IOException {
        UrlReader urlReader = new UrlReader();
        String endPoint = urlReader.postUrl();
        finalUrl = commonBaseUrl.concat(endPoint);
        return finalUrl;
    }
}
