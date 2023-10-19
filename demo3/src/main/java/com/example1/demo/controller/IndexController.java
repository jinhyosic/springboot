package com.example1.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping(value = "/")
    public String getCustomerList(Model model){

        return "forward:/bookWithThymeleaf/bookList";
    }
}
