package com.myframework.pojos;

import com.poiji.annotation.ExcelCellName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BookingDates {
    @ExcelCellName("checkin")
    private String checkin;
    @ExcelCellName("checkout")
    private String checkout;
}
