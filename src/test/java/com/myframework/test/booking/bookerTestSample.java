package com.myframework.test.booking;

import com.myframework.payload.booking.booker_payload;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.myframework.test.BaseTest;
import RestUtils.RestUtils;
import utils.JsonUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class bookerTestSample extends BaseTest {

    @Test(enabled = false)
    public void getBookingIdsBasicTest() {
        String response = RestAssured.given().baseUri(theBaseUri).
                contentType(ContentType.JSON).log().all().
                body("{\n" +
                        "    \"firstname\": \"Jim\",\n" +
                        "    \"lastname\": \"Brown\",\n" +
                        "    \"totalprice\": 111,\n" +
                        "    \"depositpaid\": true,\n" +
                        "    \"bookingdates\": {\n" +
                        "        \"checkin\": \"2018-01-01\",\n" +
                        "        \"checkout\": \"2019-01-01\"\n" +
                        "    },\n" +
                        "    \"additionalneeds\": \"Breakfast\"\n" +
                        "}").when().post().then().log().all().log().all().
                extract().response().asString();

        JsonPath json = new JsonPath(response);

        String bookingId = json.getString("bookingid");
        System.out.println("booking id is " + bookingId);

        String firstName = json.getString("booking.firstname");
        System.out.println("first name is " + firstName);

        String bookingDate = json.getString("booking.bookingdates.checkin");
        System.out.println("first name is " + bookingDate);
    }

    @Test(enabled = false)
    public void getBookingMethod() {
        String uri="https://restful-booker.herokuapp.com";
        String path= "/com/myframework/test/booking";
        String body="{\n" +
                "    \"firstname\": \"Jim\",\n" +
                "    \"lastname\": \"Brown\",\n" +
                "    \"totalprice\": 111,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2018-01-01\",\n" +
                "        \"checkout\": \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";

        String response = RestUtils.performPost(uri,path,body,new HashMap<>());
        JsonPath json = new JsonPath(response);

        String bookingId = json.getString("bookingid");
        System.out.println("booking id is " + bookingId);
    }

    @Test(enabled = false)
    public void getBookingMethod2() {
        String uri="https://restful-booker.herokuapp.com";
        String path= "/com/myframework/test/booking";
        String body= booker_payload.getBookingPayload("namea","nameb");

        String response = RestUtils.performPost(uri,path,body,new HashMap<>());
        JsonPath json = new JsonPath(response);

        String bookingId = json.getString("bookingid");
        System.out.println("booking id is " + bookingId);
    }

    @Test(enabled = false)
    public void getBookingMethod3() {
        Map<String, Object> body= booker_payload.getBookingPayloadMap("namec","named");
        String response = RestUtils.performPost(theBaseUri,"",body,new HashMap<>());
        JsonPath json = new JsonPath(response);

        String bookingId = json.getString("bookingid");
        System.out.println("booking id is " + bookingId);
    }

    @Test(enabled = false)
    public void getBookingMethod4() throws IOException {

        String environment=System.getProperty("Env");
        String env=environment==null?"qa":environment;
        Map<String,Object>data=JsonUtils.getJsoDataAsMap("/booking/qa/bookingData.json");
        String url=data.get("endPoint").toString();
        Map<String, Object> body= booker_payload.getBookingPayloadMap("namec","named");
        String response = RestUtils.performPost(url,"",body,new HashMap<>());
        JsonPath json = new JsonPath(response);

        String bookingId = json.getString("bookingid");
        System.out.println("booking id is " + bookingId);
        Assert.assertTrue(!bookingId.isBlank()|| !bookingId.isEmpty() );
    }

}
