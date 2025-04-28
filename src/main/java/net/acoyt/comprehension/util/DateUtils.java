package net.acoyt.comprehension.util;

import java.time.LocalDate;

public class DateUtils {
    private static final LocalDate today = LocalDate.now(); // Gets today's date and time
    // Checks if today is 9/12
    public static final boolean isAcoBirthday = LocalDate.of(today.getYear(), 9, 12).compareTo(today) * today.compareTo(LocalDate.of(today.getYear(), 9, 12)) >= 0;
}
