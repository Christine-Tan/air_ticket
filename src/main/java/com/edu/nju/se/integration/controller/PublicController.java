package com.edu.nju.se.integration.controller;

import com.edu.nju.se.integration.tool.*;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * Created by darxan on 2017/2/14.
 */
@Controller
@RequestMapping(value = "/public")
public class PublicController {



    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class,
                new CustomDateEditor(DateFormatter.dateFormat, false));
    }

}
