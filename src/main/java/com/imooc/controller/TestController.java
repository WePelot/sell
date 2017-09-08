/*
 * Copyright (c) 2001-2017 GuaHao.com Corporation Limited. All rights reserved. 
 * This software is the confidential and proprietary information of GuaHao Company. 
 * ("Confidential Information"). 
 * You shall not disclose such Confidential Information and shall use it only 
 * in accordance with the terms of the license agreement you entered into with GuaHao.com.
 */
package com.imooc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 测试的Controller
 *
 * @author hongcj
 * @version V1.0
 * @since 2017-09-08 14:14
 */
@Controller
public class TestController {

    @GetMapping("/jqGrid")
    public ModelAndView view(){
        return new ModelAndView("jqGrid");
    }

}
