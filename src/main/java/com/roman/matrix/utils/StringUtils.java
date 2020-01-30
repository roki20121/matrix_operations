package com.roman.matrix.utils;

public class StringUtils {

    public static double[] convertToLongArray(String[] strings) {
        double[] longs = new double[strings.length];

        for (int i = 0; i < strings.length; i++) {
            longs[i] = Double.parseDouble(strings[i].replace(',', '.'));
        }
        return longs;
    }

}
