package booker;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import payloads.booker_payload;
import utils.RestUtils;

import java.util.HashMap;
import java.util.Map;

public class BookerTest {

    @Test
    public void getBookingIdsBasicTest() {
        String response = RestAssured.given().baseUri("https://restful-booker.herokuapp.com").
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
                        "}").when().post("/booking").then().log().all().log().all().
                extract().response().asString();

        JsonPath json = new JsonPath(response);

        String bookingId = json.getString("bookingid");
        System.out.println("booking id is " + bookingId);

        String firstName = json.getString("booking.firstname");
        System.out.println("first name is " + firstName);

        String bookingDate = json.getString("booking.bookingdates.checkin");
        System.out.println("first name is " + bookingDate);
    }

    @Test
    public void getBookingMethod() {
        String uri="https://restful-booker.herokuapp.com";
        String path="/booking";
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

    @Test
    public void getBookingMethod2() {
        String uri="https://restful-booker.herokuapp.com";
        String path="/booking";
        String body= booker_payload.getBookingPayload("namea","nameb");

        String response = RestUtils.performPost(uri,path,body,new HashMap<>());
        JsonPath json = new JsonPath(response);

        String bookingId = json.getString("bookingid");
        System.out.println("booking id is " + bookingId);
    }

    @Test
    public void getBookingMethod3() {
        String uri="https://restful-booker.herokuapp.com";
        String path="/booking";
        Map<String, Object> body= booker_payload.getBookingPayloadMap("namec","named");

        String response = RestUtils.performPost(uri,path,body,new HashMap<>());
        JsonPath json = new JsonPath(response);

        String bookingId = json.getString("bookingid");
        System.out.println("booking id is " + bookingId);
    }
}
