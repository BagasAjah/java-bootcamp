package com.mitrais.util;

import java.util.regex.Pattern;

public class FormatUtils {

    public static boolean isNumeric(String inputString) {
        final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if (inputString == null) {
            return false;
        }
        return pattern.matcher(inputString).matches();
    }
}
