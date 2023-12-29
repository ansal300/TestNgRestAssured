package com.myframework.payload.booking;

import com.myframework.pojos.Booking;
import com.myframework.pojos.BookingDates;
import enums.DataEnum;
import enums.DatesEnum;
import net.datafaker.Faker;
import org.apache.commons.lang3.RandomStringUtils;
import utils.RandomDataGenerator;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static utils.RandomDataGenerator.*;

public class booker_payload {

    public static String getBookingPayload(String firstname, String lastname) {
        return "{\n" +
                "    \"firstname\": \"" + firstname + "\",\n" +
                "    \"lastname\": \"" + lastname + "\",\n" +
                "    \"totalprice\": 111,\n" +
                "    \"depositpaid\": true,\n" +
                "    \"bookingdates\": {\n" +
                "        \"checkin\": \"2018-01-01\",\n" +
                "        \"checkout\": \"2019-01-01\"\n" +
                "    },\n" +
                "    \"additionalneeds\": \"Breakfast\"\n" +
                "}";
    }

    public static Map<String, Object> getBookingPayloadMap(String firstname, String lastname) {
        Map<String, Object> dates = new HashMap<>();
        dates.put("checkin", "2018-01-01");
        dates.put("checkout", "2019-01-01");

        Map<String, Object> map = new HashMap<>();
        map.put("firstname", firstname);
        map.put("lastname", lastname);
        map.put("totalprice", 111);
        map.put("depositpaid", true);
        map.put("bookingdates", dates);
        map.put("additionalneeds", "Breakfast");

        return map;
    }

    public static Map<String, Object> getBookingPayloadMap() {
        Faker faker = new Faker();
        String checkout = LocalDate.now().minusYears(2).toString();
        String checkIn = LocalDate.now().minusYears(2).minusDays(1).toString();
        Map<String, Object> dates = new HashMap<>();
        /*dates.put("checkin","2018-01-01");
        dates.put("checkout","2019-01-01");*/

        dates.put("checkin", checkIn);
        dates.put("checkout", checkout);


        Map<String, Object> map = new HashMap<>();
        map.put("firstname", faker.name().firstName());
        map.put("lastname", faker.name().lastName());
        map.put("totalprice", faker.number().numberBetween(100, 999));
        map.put("depositpaid", true);
        map.put("bookingdates", dates);
        map.put("additionalneeds", faker.food().fruit());

        return map;
    }

    public static Booking getBookingPayLoadFromPojo()
    {
        String checkout = getDates(DatesEnum.ONE_DAY_AFTER_TODAYS_DATE);
        String checkIn = getDates(DatesEnum.TODAYS_DATE);

        BookingDates dates=BookingDates.builder().
                checkin(checkIn).checkout(checkout).build();

        Booking booking=Booking.builder().
                bookingdates(dates).
                lastname(getRandomData(DataEnum.LASTNAME)).
                firstname(getRandomData(DataEnum.FIRSTNAME)).
                totalprice(getRandomNumbers()).
                depositpaid(true).
                additionalneeds(getRandomData(DataEnum.BREAKFAST)).build();

        return booking;

    }
}
