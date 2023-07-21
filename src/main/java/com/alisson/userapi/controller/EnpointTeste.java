package com.alisson.userapi.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/teste")
public class EnpointTeste {

    @GetMapping(value = "/open")
    public String openEndPoint () {

        return "Endpoint sem segurança";
    }

    @GetMapping(value = "/close")
    public String closeEndPoint () {

        return "Endpoint esta com segurança";
    }

}
