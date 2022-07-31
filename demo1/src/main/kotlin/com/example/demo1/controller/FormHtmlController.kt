package com.example.demo1.controller

import com.example.demo1.domain.Users
import com.example.demo1.repositories.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod

@Controller
class FormHtmlController {

    @Autowired
    lateinit var usersRepository: UsersRepository

    @RequestMapping(value = ["/form", "/form/{userId}"], method = [RequestMethod.GET])
    fun getForm(
        @PathVariable("userId") userid:Long?,
        users:Users?,
        model : Model
    ):String{

        val users = if(userid == null){
            Users(null,null,null)
        } else {
            usersRepository.findByIdOrNull(userid)
        }

        model.addAttribute("users",users)
        return "form/index"
    }

    @RequestMapping(value = ["/form"], method = [RequestMethod.POST])
    fun postForm(
        users:Users?,
        model : Model
    ):String {

        if (users == null) {     // 왜 user가 null User로 들어오지 않을까??
            println("user는 널입니다")
        }

        users?.let {
            val bcrypt = BCryptPasswordEncoder(11)
            it.pwd = bcrypt.encode(it.pwd)
            //println(it)   //it으로 할 수도 있고 user로 할 수도 있음
            usersRepository.save(it)
        }
        val _users = Users()         //제출하고 제출란을 빈칸으로 만들어 주기 위해 null User를 model ui에 넣어줌

        model.addAttribute("users", _users)
        return "form/index"

    }
}