package com.myframework.test.booking;

import RestUtils.RestUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.myframework.apiMethods.BookingApi;
import com.myframework.payload.booking.booker_payload;
import com.myframework.pojos.BookinResponse;
import com.myframework.pojos.Booking;
import com.myframework.pojos.BookingDates;
import com.myframework.test.BaseTest;
import com.poiji.bind.Poiji;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import utils.ExcelUtils;
import utils.JsonUtils;

import java.awt.print.Book;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class bookerTest extends BookingApi {


    @Test()
    public void getBookingMethod4() throws IOException {
        Map<String, Object> body = booker_payload.getBookingPayloadMap();
        Response response = createBooking(body);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test()
    public void getBookingMethod5() throws IOException {
        Booking booking = booker_payload.getBookingPayLoadFromPojo();
        Response response = createBooking(booking);
        Assert.assertEquals(response.statusCode(), 200);
    }

    @Test()
    public void getBookingMethod6() throws IOException {
        Booking booking = booker_payload.getBookingPayLoadFromPojo();
        Response response = createBooking(booking);
        Assert.assertEquals(response.statusCode(), 200);

        String bookingId = response.jsonPath().getString("bookingid");

        ObjectMapper mapper = new ObjectMapper();
        BookinResponse responseObject = mapper.readValue(response.getBody().asString(), BookinResponse.class);

        Assert.assertEquals(responseObject.getBooking(), booking);

        Assert.assertTrue(responseObject.getBooking().equals(booking));
    }

    @Test(dataProvider ="bookingData",enabled = false)
    public void getBookingMethod7(Booking booking) throws IOException {
        Response response = createBooking(booking);
        Assert.assertEquals(response.statusCode(), 200);

        String bookingId = response.jsonPath().getString("bookingid");

        ObjectMapper mapper = new ObjectMapper();
        BookinResponse responseObject = mapper.readValue(response.getBody().asString(), BookinResponse.class);

        Assert.assertEquals(responseObject.getBooking(), booking);

        Assert.assertTrue(responseObject.getBooking().equals(booking));
    }

    @Test(dataProvider ="bookingDataPoiji",enabled = false)
    public void getBookingMethod8(Booking booking) throws IOException {

        BookingDates dates=BookingDates.builder().
                checkin("2018-01-01").checkout("2019-01-01").build();
        booking.setBookingdates(dates);

        Response response = createBooking(booking);
        Assert.assertEquals(response.statusCode(), 200);

        String bookingId = response.jsonPath().getString("bookingid");

        ObjectMapper mapper = new ObjectMapper();
        BookinResponse responseObject = mapper.readValue(response.getBody().asString(), BookinResponse.class);

        Assert.assertEquals(responseObject.getBooking(), booking);

        Assert.assertTrue(responseObject.getBooking().equals(booking));
    }

    @DataProvider(name="bookingData")
    public Iterator<Booking> getTestData() throws IOException {
        List<LinkedHashMap<String, String>> map = ExcelUtils.
                ReturnDataFromExcelAsMap("src/test/resources/booking/TestData.xlsx", "Sheet1");

        List<Booking>bookings=new ArrayList<>();
        for (LinkedHashMap<String, String> data : map) {

            BookingDates dates=BookingDates.builder().
                    checkin(data.get("checkin")).checkout(data.get("checkout")).build();

            Booking booking = Booking.builder().
                    firstname(data.get("firstname"))
                    .lastname(data.get("lastname"))
                    .totalprice(Integer.parseInt(data.get("totalprice")))
                    .additionalneeds(data.get("additionalneeds"))
                    .bookingdates(dates)
                    .build();

            bookings.add(booking);
        }

        return bookings.iterator();
    }

    @DataProvider(name="bookingDataPoiji")
    public Iterator<Booking> getTestDataPoiji() throws IOException {
        List<Booking>bookings=Poiji.
                fromExcel(new File("src/test/resources/booking/TestData.xlsx"),Booking.class);

        return bookings.iterator();
    }

}
