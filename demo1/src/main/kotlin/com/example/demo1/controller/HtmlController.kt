package com.example.demo1.controller

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class HtmlController {

    @RequestMapping(value=["/"], method = [RequestMethod.GET])
    fun index(): String = "index"
}