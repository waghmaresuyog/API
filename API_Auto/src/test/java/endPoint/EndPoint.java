package endPoint;

import helper.UrlReader;

import java.io.IOException;

public class EndPoint extends UrlReader {
    // return the end url
    public static String endUrl;
    public static String getEndUrl;
    public static String productEndURL() {
        endUrl = "productsList";
        return endUrl;
    }
}
