package kr.frec.week1.userManager

import org.springframework.security.crypto.bcrypt.BCrypt

class User {
    val id: String
    val name: String
    private var encryptedPW: String

    constructor(id: String, name: String, pw: String) {
        this.id = id
        this.name = name
        this.encryptedPW = this.setPW(pw)
    }

    infix fun valid(pw: String): Boolean {
        return BCrypt.checkpw(pw, this.encryptedPW)
    }

    override fun toString(): String {
        return "User<id: $id, name: $name>"
    }

    private fun setPW(pw: String): String {
        return BCrypt.hashpw(pw, BCrypt.gensalt())
    }
}