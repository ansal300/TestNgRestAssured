package RestUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import reports.ExtentReport;

import java.util.Map;

public class RestUtils {

    private static RequestSpecification getRequestSpecification(String baseUri, String path, Map<String, Object> payload, Map<String, String> headers) {
        return RestAssured.given().baseUri(baseUri).
                contentType(ContentType.JSON).headers(headers).log().all().
                body(payload);
    }

    private static void printRequestLogInReport(RequestSpecification requestSpecification) {
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(requestSpecification);

        ExtentReport.logInfoDetails("Base URI: " + queryableRequestSpecification.getBaseUri());
        ExtentReport.logInfoDetails("Method: " + queryableRequestSpecification.getMethod());
        ExtentReport.logInfoDetails("Headers : " + queryableRequestSpecification.getHeaders().asList().toString());
        ExtentReport.logInfoDetails("Request Body: " + queryableRequestSpecification.getBody());

    }

    private static void printResponseLogInReport(Response response) {
        ExtentReport.logInfoDetails("Response status : " + response.getStatusCode());
        ExtentReport.logInfoDetails("Response Headers : " + response.getHeaders().asList().toString());
        ExtentReport.logInfoDetails("Response Body: " + response.getBody());
    }

    public static String performPost(String baseUri, String path, String payload, Map<String, String> headers) {
        return RestAssured.given().baseUri(baseUri).
                contentType(ContentType.JSON).headers(headers).log().all().
                body(payload).when().post(path).then().log().all().log().all().
                extract().response().asString();
    }

    public static String performPost(String baseUri, String path, Map<String, Object> payload, Map<String, String> headers) {
        return RestAssured.given().baseUri(baseUri).
                contentType(ContentType.JSON).headers(headers).log().all().
                body(payload).when().post(path).then().log().all().log().all().
                extract().response().asString();

    }

    public static Response performApiPost(String baseUri, String path, Map<String, Object> payload, Map<String, String> headers) {
        RequestSpecification requestSpecification = getRequestSpecification(baseUri, path, payload, headers);
        Response response = requestSpecification.when().post(path).then().extract().response();
        printRequestLogInReport(requestSpecification);
        printResponseLogInReport(response);
        return response;
    }

}
