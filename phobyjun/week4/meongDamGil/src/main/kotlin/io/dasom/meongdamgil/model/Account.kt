package io.dasom.meongdamgil.model

import java.time.ZonedDateTime
import javax.persistence.*

@Entity
@Table(name = "account")
class Account(

    @Id @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(name = "email", unique = true)
    val email: String,

    @Column(name = "password")
    val password: String,

    @Column(name = "user_role")
    val userRole: UserRole? = UserRole.ROLE_USER,

    @Column(name = "created_at")
    val createdAt: ZonedDateTime? = ZonedDateTime.now()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Account
        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        return email.hashCode()
    }
}

enum class UserRole {
    ROLE_USER, ROLE_ADMIN
}