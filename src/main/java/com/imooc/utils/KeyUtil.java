package com.imooc.utils;

import java.util.Random;

/**
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-07-19 14:32
 */
public class KeyUtil {

    //防止多线程时出现相同的数字
    public synchronized static  String genUniqueKey(){
        Random random = new Random();
        Integer number = random.nextInt(900000) + 100000;

        return System.currentTimeMillis() + String.valueOf(number);
    }
}
