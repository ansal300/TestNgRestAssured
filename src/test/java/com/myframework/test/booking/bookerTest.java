package com.myframework.test.booking;

import RestUtils.RestUtils;
import com.myframework.apiMethods.BookingApi;
import com.myframework.payload.booking.booker_payload;
import com.myframework.test.BaseTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.JsonUtils;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class bookerTest extends BookingApi {


    @Test
    public void getBookingMethod4() throws IOException {
        Map<String, Object> body= booker_payload.getBookingPayloadMap("namec","named");
        Response response=createBooking(body);
        Assert.assertEquals(response.statusCode(),200);
    }

}
