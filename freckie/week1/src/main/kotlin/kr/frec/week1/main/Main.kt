package kr.frec.week1.main

import kr.frec.week1.userManager.UserManager

fun main(args: Array<String>) {
    var um = UserManager(3)
    println(um.toString())

    um.newUser("freckie", "김명현", "audgus123")
    println(um.toString())

    um.login("freckie", "audgus123")
    println(um.toString())
}