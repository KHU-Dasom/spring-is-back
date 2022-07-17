package com.hi.udemy

import org.aspectj.bridge.Message
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import java.security.MessageDigest
import javax.servlet.http.HttpSession

@Controller
class HtmlController {

    @Autowired
    lateinit var repository: UserRepository

    @Autowired
    lateinit var todoRepository: TotoRepository

    @GetMapping("/")
    fun index(model:Model):String {
        model.addAttribute("title", "Home")
        return "index"
    }

    fun crypto(ss:String) : String{
        val sha=MessageDigest.getInstance(("SHA-256"))
        val hexa=sha.digest(ss.toByteArray())
        val crypto_str=hexa.fold("",{str, it -> str + "%02x".format(it)})
        return crypto_str
    }

    @GetMapping("/{formType}")
    fun htmlForm(model: Model, @PathVariable formType : String) : String?{
        var response : String? = null
        if(formType.equals("sign")){
            response = "sign"
        }
        else if (formType.equals("login")){
            response = "login"
        }
        else if (formType.equals("welcome")){
            response = "welcome"
        }
        else if (formType.equals("todopage")){
            response = "todopage"
        }

        model.addAttribute("title", response) //map 같이 키와 value가 있다

        return response
    }

    @PostMapping("/sign")
    fun postSign(model: Model,
                 @RequestParam(value = "id") userId:String,
                 @RequestParam(value= "password") password:String): String{
        try{
            val cryptoPass = crypto(password)
            repository.save(Users(userId, cryptoPass))
        } catch (e:Exception){
            e.printStackTrace()
        }
        model.addAttribute("title", "login")
        return "login"
    }

    @PostMapping("/login")
    fun postLogin(model: Model,
                  session: HttpSession,
                    @RequestParam("id") userId: String,
                    @RequestParam("password") password: String):String{
        var response : String = ""
        try{
            val cryptoPass=crypto(password)
            val dbUser = repository.findByUserId(userId)
            println(dbUser.toString())
            if(dbUser != null){
                val dbPass = dbUser.password
                if(cryptoPass.equals(dbPass)){
                    session.setAttribute("userId",dbUser.userId)
                    model.addAttribute("title","welcome")
                    model.addAttribute("userId", userId)
                    response = "welcome"
                }
                else{
                    model.addAttribute("title", "login")
                    response = "login"
                }

            }
        } catch (e:Exception){
            e.printStackTrace()
        }
        return response
    }

    @PostMapping("/todopage")
    fun postTodo(model: Model,
                 @RequestParam("id") userId: String,
                 @RequestParam("todotext") todo: String) : String{
        try{
            todoRepository.save(Todo(userId, todo))
        } catch (e:Exception){
            e.printStackTrace()
        }
        model.addAttribute("title", "todopage")
        return "todopage"
    }
}