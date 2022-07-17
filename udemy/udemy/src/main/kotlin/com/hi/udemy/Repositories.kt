package com.hi.udemy

import org.springframework.data.repository.CrudRepository

interface UserRepository:CrudRepository<Users,Long> {
    fun findByUserId(userId:String):Users
}

interface TotoRepository:CrudRepository<Todo,Long> {
    fun findByUserId(userId:String):Todo
}