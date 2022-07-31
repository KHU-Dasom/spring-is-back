package com.example.demo1.repositories

import com.example.demo1.domain.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UsersRepository: JpaRepository<Users, Long> {
    fun findByName(name:String): Users?
}