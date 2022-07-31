package com.example.demo1.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class HomeHtmlController {
    @GetMapping(value = ["/home"])
    fun homePage(): String = "form/home"
}