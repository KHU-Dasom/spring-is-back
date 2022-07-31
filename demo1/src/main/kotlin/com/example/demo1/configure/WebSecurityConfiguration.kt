package com.example.demo1.configure

import com.example.demo1.services.UsersCustomService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Configurable
import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.dao.DaoAuthenticationProvider
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.server.SecurityWebFilterChain

@Configurable
@EnableWebSecurity
class WebSecurityConfiguration {

    //우리가 사용하는 userDetailsService를 가져와야 됨
    @Autowired
    lateinit var usersCustomService: UsersCustomService

    @Bean
    fun encoder(): PasswordEncoder=BCryptPasswordEncoder(11)

//    override fun configure(auth: AuthenticationManagerBuilder?) {  //얘는 인증
//        auth?.authenticationProvider(authenticationProvider())
//    }
//    @Bean
//    fun userDetailsService() : UserDetailsService{
//        return usersCustomService
//    }
    @Bean
    fun authenticationProvider():DaoAuthenticationProvider{
        val authProvider=DaoAuthenticationProvider()
        authProvider.setUserDetailsService(usersCustomService)
        authProvider.setPasswordEncoder(encoder())
        return authProvider
    }

//    override fun configure(http: HttpSecurity?) { //얘는 권한
//        http
//            ?.csrf()?.disable()
//            ?.authorizeRequests()
//            ?.antMatchers("/resources/**")?.permitAll()  // permitAll : ADMIN이든 MEMBER든 다 허용
//            ?.anyRequest()?.permitAll()
//            ?.and()
//            ?.formLogin()    //custom login page에 대한 정보
//            ?.loginPage("/login")
//            ?.loginProcessingUrl("/login") // login page의 <form>의 action url이 뭐냐
//            ?.defaultSuccessUrl("/home") //login이 성공하면 어느 url로 이동할 것이냐
//            ?.and()
//            ?.logout() //logout에 대한 정보
//            ?.logoutUrl("/logout")
//    }

    @Bean
    fun filterChain(http: HttpSecurity): SecurityFilterChain{
        http
            .csrf()?.disable()
            ?.headers()?.frameOptions()?.disable() //해주면 h2 안뜨는 거 해결..
            ?.and()
            ?.authorizeRequests()
            ?.antMatchers("/resources/**")?.permitAll()  // permitAll : ADMIN이든 MEMBER든 다 허용
            ?.antMatchers("/h2/**")?.permitAll()
            ?.anyRequest()?.permitAll()
            ?.and()
            ?.formLogin()    //custom login page에 대한 정보
            ?.loginPage("/login")
            ?.loginProcessingUrl("/login") // login page의 <form>의 action url이 뭐냐
            ?.defaultSuccessUrl("/home") //login이 성공하면 어느 url로 이동할 것이냐
            ?.and()
            ?.logout() //logout에 대한 정보
            ?.logoutUrl("/logout")
        return http.build()
    }


}