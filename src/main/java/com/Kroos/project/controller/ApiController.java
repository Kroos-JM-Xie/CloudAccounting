package com.Kroos.project.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {
    private final static Logger log= LoggerFactory.getLogger(ApiController.class);

    @GetMapping("/hello")
    public String test(){
        return "index";
    }

    //public String home() {
    //	System.out.println(calculate());
    //	return "Hello World!"+calculate();
    //}
    @GetMapping("/calculate/{a}")
    public int calculate(@PathVariable("a") Integer a){
        log.warn("请求的a="+a);
    	int i=1;
    	return a+(++i)+i++;
    }
}
