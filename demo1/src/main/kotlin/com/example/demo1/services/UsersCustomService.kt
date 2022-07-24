package com.example.demo1.services

import com.example.demo1.repositories.UsersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UsersCustomService:UserDetailsService {

    @Autowired
    lateinit var usersRepository: UsersRepository

    override fun loadUserByUsername(username: String?): UserDetails? {
        return usersRepository.findByName(username!!)
    }
}