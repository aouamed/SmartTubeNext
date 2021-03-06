package com.liskovsoft.smartyoutubetv2.common.utils;

import android.content.Context;
import com.liskovsoft.sharedutils.locale.LocaleUtility;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatter {
    public static String getCurrentTimeShort(Context context) {
        // details: https://docs.oracle.com/javase/7/docs/api/java/text/SimpleDateFormat.html
        String pattern = is24HourLocale(context) ? "EEE d MMM H:mm" : "EEE d MMM h:mm a";

        SimpleDateFormat serverFormat = new SimpleDateFormat(pattern, Locale.getDefault());
        String currentTime = serverFormat.format(new Date());

        return String.format("%1$s", currentTime);
    }

    private static boolean is24HourLocale(Context context) {
        Locale locale = LocaleUtility.getCurrentLocale(context);

        java.text.DateFormat natural =
                java.text.DateFormat.getTimeInstance(
                        java.text.DateFormat.LONG, locale);

        if (natural instanceof SimpleDateFormat) {
            SimpleDateFormat sdf = (SimpleDateFormat) natural;
            String pattern = sdf.toPattern();
            if (pattern.indexOf('H') >= 0) {
                return true;
            } else {
                return false;
            }
        } else {
            return true;
        }
    }
}
