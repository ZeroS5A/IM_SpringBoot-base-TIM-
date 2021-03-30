package com.controller;


import com.commom.Result;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/squire")
public class SquireController {

    @RequestMapping("/getSquireList")
    public Result getSquireList(@RequestBody Map userData){

        return null;
    }
}
