package com.myframework.pojos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.poiji.annotation.ExcelCellName;
import lombok.*;


@Data
/*@NoArgsConstructor -- this is for creating a constructor without any arg, in this way
we can assign default values to each variables in the class, */
/*@AllArgsConstructor -- if no arg constructor is present,
        all arg constructor should also present for the @builder to work*/
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode   //this is to compare two objects of this pojo class
@JsonIgnoreProperties(ignoreUnknown = true)  // when comparing response and request objects, response may be having some additional fields which are not neccessary to verify, if we dont have this annotation , an unrecognized exception may throw
@Builder
public class Booking {
    @ExcelCellName("firstname")
    private String firstname;
    //private String firstname=RandonDatGenerator.getFirstName(); -- example when using no arg constructor
    @ExcelCellName("lastname")
    private String lastname;
    @ExcelCellName("totalprice")
    private int totalprice;
    @ExcelCellName("depositpaid")
    private boolean depositpaid;
    @ExcelCellName("bookingdates")
    private BookingDates bookingdates;
    @ExcelCellName("additionalneeds")
    private String additionalneeds;

}
