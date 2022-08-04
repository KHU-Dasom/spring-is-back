package io.dasom.grpcspringboot.controller

import io.dasom.grpcspringboot.dto.PersonDto
import io.dasom.grpcspringboot.service.PersonClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class PersonClientController {

    @Autowired
    private lateinit var personClientService: PersonClientService

    @RequestMapping("/person")
    fun personController(@RequestParam("id") id: Int): PersonDto {
        return personClientService.getPerson(id)
    }
}
