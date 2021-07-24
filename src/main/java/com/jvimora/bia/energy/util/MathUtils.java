package com.jvimora.bia.energy.util;

public class MathUtils {

    public static Double parseDouble(Double number) {
        return Math.round(number * 100.0) / 100.0;
    }


}
