//Created by Malgorzata Sliwa 2018

package com.agh.project.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BasicController {

    @RequestMapping("/")
    public String basic(){
        return "Start Application";
    }
}
