package br.com.senior.util;

import br.com.senior.exception.IllegalArgumentException;

public class Utils {

    public static void assertArgumentNotEmpty(String field, String errMessage) {
	if (field == null || field.isEmpty())
	    throw new IllegalArgumentException(errMessage);
    }

    public static void assertArgumentNotNull(Object obj, String errMessage) {
	if (obj == null)
	    throw new IllegalArgumentException(errMessage);
    }

}
