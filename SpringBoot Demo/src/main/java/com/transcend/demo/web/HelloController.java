package com.transcend.demo.web;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.text.DateFormat;
import java.util.Date;

@Controller
public class HelloController {

    private Logger logger = LogManager.getLogger(LogManager.ROOT_LOGGER_NAME);

    @RequestMapping("/hello")
    public String hello(Model model) {

        model.addAttribute("now", DateFormat.getDateInstance().format(new Date()));

        logger.info("model.addAttribute" + DateFormat.getDateInstance().format(new Date()));
        return "hello";
    }

}
