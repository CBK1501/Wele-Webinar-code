package com.wele.learning.utils;

import java.util.Random;

public class CodeGenerator {

    private static final char[] ALPHAPET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final char[] NUMERIC = "0123456789".toCharArray();

    private static int CODESIZE = 4;

    public static String randomAlphaNumeric() {

        Random rnd = new Random();
        StringBuilder builder = new StringBuilder();
        while (CODESIZE-- != 0) {
            //System.out.println("rnd.nextInt(): " + rnd.nextInt(ALPHAPET.length));
            //builder.append(ALPHA_NUMERIC_STRING1[rnd.nextInt(ALPHA_NUMERIC_STRING1.length)]);
            char newChar = ALPHAPET[rnd.nextInt(ALPHAPET.length)];
            String s = builder.toString();
            // System.out.println("s: " + s + " count: " + count);
            if (CODESIZE == 0) {
                //  System.out.println("s: " + s);
                boolean isInt = IsInteger(s);
                // System.out.println("isInt: " + isInt);
                if (isInt) {
                    builder.append(newChar);
                } else {
                    builder.append(NUMERIC[rnd.nextInt(NUMERIC.length)]);
                }
            } else {
                builder.append(newChar);
            }
        }

        System.out.println("code************: " + builder.toString());
        return builder.toString();
    }

    private static Boolean IsInteger(String str) {
        int length = str.length(), c = 0;

        for (int i = 0; i < length; i++) {
            c = (int) str.charAt(i);
            if (Character.isDigit(c)) {
                return true;
            }
        }
        return false;
    }
}
