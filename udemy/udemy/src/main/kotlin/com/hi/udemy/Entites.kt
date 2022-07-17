package com.hi.udemy


import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.JoinColumn


@Entity
class Users(
    var userId: String,
    var password: String,
    @Id
    @GeneratedValue//(strategy = GenerationType.AUTO)
    var id: Long?=null) //@Id : prime key, @GeneratedValue : 자동생성)

@Entity
class Todo(
    val userId : String,
    var context : String,
    @Id
    @GeneratedValue
    var id: Long?=null
)

//CREATE TABLE User (
//        userId TEST NOT NULL,
//        password VARCHAR(20) NOT NULL,
//        id LONG NOT NULL PRIME KEY
//);

