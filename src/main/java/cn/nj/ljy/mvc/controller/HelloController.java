package cn.nj.ljy.mvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.nj.ljy.mvc.common.LjyResponse;

@Controller
@RequestMapping(value = "test")
public class HelloController {

    private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        LOGGER.info("method hello executed ");
        return "Hello ,spring mvc!";
    }

    @RequestMapping("/json")
    @ResponseBody
    public LjyResponse<Boolean> json() {
        LjyResponse<Boolean> response = new LjyResponse<Boolean>();
        response.setCode("0");
        response.setDesc("success");
        response.setContent(true);
        return response;
    }

}
