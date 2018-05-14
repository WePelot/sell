package com.imooc.utils;

/**
 * @author hongcj
 * @version V1.0
 * @since 2017-08-17 14:22
 */
public class MathUtil {
    private static final Double MONEY_RANGE = 0.01;

    //比较金额是否相同，这里少于0.01则认为两个金额相同
    public static boolean equal(Double a, Double b) {
        double abs = Math.abs(a - b);
        if (abs < MONEY_RANGE) {
            return true;
        } else {
            return false;
        }
    }
}
