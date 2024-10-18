package com.example.demo.web.controller;

import com.example.demo.service.FauxService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FauxController {

    FauxService fauxService;

    public FauxController(FauxService fauxService) {
        this.fauxService = fauxService;
    }

    @RequestMapping("/faux")
    @ResponseBody
    public String getFaux() {

        System.out.println("In Faux");

        return fauxService.getDatasource();
    }
}
