package com.example.demo1.configure

import com.example.demo1.services.UsersCustomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@Configurable
@EnableWebSecurity
class WebSecurityConfiguration:WebSecurityConfigurerAdapter() {

    //우리가 사용하는 userDetailsService를 가져와야 됨
    @Autowired
    lateinit var usersCustomService: UsersCustomService

    @Bean
    fun encoder(): PasswordEncoder=BCryptPasswordEncoder(11)

    override fun configure(auth: AuthenticationManagerBuilder?) {  //얘는 인증
        auth?.authenticationProvider(authenticationProvider())
    }

    @Bean
    fun authenticationProvider():DaoAuthenticationProvider{
        val authProvider=DaoAuthenticationProvider()  //DaoAuthenticationProvider 기본으로 제공하는 객체
        authProvider.setUserDetailsService(usersCustomService)
        authProvider.setPasswordEncoder(encoder())
        return authProvider
    }

    override fun configure(web: WebSecurity?) { //얘는 권한
        http
            ?.csrf()?.disable()
            ?.authorizeRequests()
            ?.antMatchers("/resources/**")?.permitAll()  // permitAll : ADMIN이든 MEMBER든 다 허용
            ?.anyRequest()?.permitAll()
            ?.and()
            ?.formLogin()    //custom login page에 대한 정보
            ?.loginPage("/login")
            ?.loginProcessingUrl("/login") // login page의 <form>의 action url이 뭐냐
            ?.defaultSuccessUrl("/home") //login이 성공하면 어느 url로 이동할 것이냐
            ?.and()
            ?.logout() //logout에 대한 정보
            ?.logoutUrl("/logout")
    }
}