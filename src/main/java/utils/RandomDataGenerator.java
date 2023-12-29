package utils;

import enums.DataEnum;
import enums.DatesEnum;
import net.datafaker.Faker;

import java.time.LocalDate;

public class RandomDataGenerator {

    private static Faker faker=new Faker();

    public static String getRandomData(DataEnum data)
    {
        String randomData;

        switch (data)
        {
            case FIRSTNAME -> randomData= faker.name().firstName();
            case LASTNAME -> randomData= faker.name().lastName();
            case BREAKFAST -> randomData= faker.food().dish();
            default -> randomData= "abc";
        }

        return randomData;
    }

    public static String getDates(DatesEnum date)
    {
        String dates;
        String localDate;

        switch (date)
        {
            case TODAYS_DATE ->
                    localDate=LocalDate.now().toString();
            case ONE_DAY_AFTER_TODAYS_DATE ->
                    localDate=LocalDate.now().plusDays(1).toString();
            case YEAR -> localDate= String.valueOf(LocalDate.now().getYear());
            default -> localDate= "abc";
        }
        return localDate;
    }

    public static int getRandomNumbers()
    {
        return faker.number().numberBetween(100, 999);
    }
}
