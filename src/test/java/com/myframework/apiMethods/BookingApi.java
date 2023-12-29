package com.myframework.apiMethods;

import RestUtils.RestUtils;
import com.myframework.payload.booking.booker_payload;
import com.myframework.pojos.Booking;
import com.myframework.test.Base;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class BookingApi {

    public Response createBooking(Map<String, Object> body)
    {
        String url= (String) Base.data.get("endPoint");
        Response response = RestUtils.performApiPost(url,"",body,new HashMap<>());
        return response;
    }

    public Response createBooking(Booking booking)
    {
        String url= (String) Base.data.get("endPoint");
        Response response = RestUtils.performApiPost(url,"",booking,new HashMap<>());
        return response;
    }
}
