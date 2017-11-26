package com.intellect.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Utils {

	public static boolean validateDate (String strDate) {
		SimpleDateFormat format = new SimpleDateFormat("dd-MMM-YYYY");
        try {
            Date parsedDate = format.parse(strDate);
            if(parsedDate.before(new Date())) {
            	return true;
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return false;
	}
}
