package com.example.demo1.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping

@Controller
class LoginHtmlController {

    @GetMapping(value = ["/login"])
    fun loginPage(): String="form/login"
}