package utils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import java.util.Map;

public class RestUtils {

    public static String performPost(String baseUri, String path,String payload, Map<String,String> headers)
    {
        return RestAssured.given().baseUri(baseUri).
                contentType(ContentType.JSON).headers(headers).log().all().
                body(payload).when().post(path).then().log().all().log().all().
                extract().response().asString();
    }

    public static String performPost(String baseUri, String path,Map<String,Object> payload, Map<String,String> headers)
    {
        return RestAssured.given().baseUri(baseUri).
                contentType(ContentType.JSON).headers(headers).log().all().
                body(payload).when().post(path).then().log().all().log().all().
                extract().response().asString();
    }
}
