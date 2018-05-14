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
