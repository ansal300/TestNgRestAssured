package com.myframework.payload.booking;

import java.util.HashMap;
import java.util.Map;

public class booker_payload {

    public static String getBookingPayload(String firstname,String lastname)
    {
        return "{\n" +
                "    \"firstname\": \""+firstname+"\",\n" +
                "    \"lastname\": \""+lastname+"\",\n" +
                "    \"totalprice\": 111,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2018-01-01\",\n" +
                "        \"checkout\": \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";
    }

    public static Map<String, Object> getBookingPayloadMap(String firstname, String lastname)
    {
        Map<String,Object>dates=new HashMap<>();
        dates.put("checkin","2018-01-01");
        dates.put("checkout","2019-01-01");

        Map<String,Object>map=new HashMap<>();
        map.put("firstname",firstname);
        map.put("lastname",lastname);
        map.put("totalprice",111);
        map.put("depositpaid",true);
        map.put("bookingdates",dates);
        map.put("additionalneeds","Breakfast");

        return map;
    }
}
