package com.ubs.opsit.interviews;

import java.util.regex.Pattern;
import org.apache.commons.lang.StringUtils;

/**
 * A validator to validate time in 24 hour format.
 * <p/>
 * Created by antonguba on 2/13/2016.
 */
public class TimeValidator {

    /**
     * Time in 24-Hour Format Regular Expression Pattern.
     */
    public static final String TIME_FORMAT_REGEXP = "([01]?[0-9]|2[0-4]):[0-5][0-9]:[0-5][0-9]";

    private static final Pattern PATTERN = Pattern.compile(TIME_FORMAT_REGEXP);

    public static boolean isValid(String time) {
        return !StringUtils.isBlank(time) && PATTERN.matcher(time).matches();
    }
}
