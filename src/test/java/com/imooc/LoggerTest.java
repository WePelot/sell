package com.imooc;

import com.imooc.dataobject.ProductCategory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import lombok.extern.slf4j.Slf4j;

/**
 * @author hongcj
 *
 * @version V1.0
 * @since 2017-07-12 10:28
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {
//    private final Logger log = LoggerFactory.getLogger(LoggerTest.class);

    @Test
    public void test1(){
        String name = "hello";
        String password = "world";
        log.info("name:{}, passwork:{}",name,password);
        log.debug("debug..");
        log.info("info..");
        log.error("err...");
    }
}
