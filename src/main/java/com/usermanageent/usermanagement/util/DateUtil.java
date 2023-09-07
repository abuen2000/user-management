package com.usermanageent.usermanagement.util;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
public class DateUtil {
    // Date format pattern
    private static final String DATE_FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    // Parse a date string into a LocalDateTime object
    public LocalDateTime parseStringToLocalDateTime(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_FORMAT_PATTERN);
        return LocalDateTime.parse(dateString, formatter);
    }
}
