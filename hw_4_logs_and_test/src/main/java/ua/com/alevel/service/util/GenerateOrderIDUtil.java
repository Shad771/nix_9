package ua.com.alevel.service.util;

import java.time.format.DateTimeFormatter;
import java.time.LocalDate;

public final class GenerateOrderIDUtil {

    private GenerateOrderIDUtil() { }

    private static int orderNumberPerDay = 0;
    private static final LocalDate date = LocalDate.now();
    private static LocalDate lastDate = LocalDate.now();
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");

    public static String generate(String id) {
        if (date.isAfter(lastDate)) {
            orderNumberPerDay = 0;
        }
        lastDate = date;
        orderNumberPerDay++;
        return (id + "_" + date.format(dateFormat) + "_" + orderNumberPerDay);
    }
}