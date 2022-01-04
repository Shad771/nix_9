package ua.com.alevel.service.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class GenerateOrderIDUtil {

    private static final LocalDate date = LocalDate.now();
    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyyMMdd");
    private static int orderNumberPerDay = 0;
    private static LocalDate lastDate = LocalDate.now();
    private GenerateOrderIDUtil() {
    }

    public static String generate(String id) {
        if (date.isAfter(lastDate)) {
            orderNumberPerDay = 0;
        }
        lastDate = date;
        orderNumberPerDay++;
        return (id + "_" + date.format(dateFormat) + "_" + orderNumberPerDay);
    }
}