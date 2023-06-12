package com.hacheery.dailychallenger.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by HachNV on 12/06/2023
 */
public class DateUtils {
    public static String getDateYYYYMMDD(LocalDate dateObj) {
        DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dateObj.format(dateFormat);
    }
}
