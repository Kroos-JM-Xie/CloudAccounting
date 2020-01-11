package com.Kroos.project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api")
public class ApiController {
    @RequestMapping("/hello")
    public ModelAndView test(ModelAndView modelAndView){
        modelAndView.setViewName("index");
        return modelAndView;
    }
}
