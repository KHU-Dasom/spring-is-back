package io.dasom.meongdamgil.model

import javax.persistence.*

@Entity
data class Member(
    @Id @Column(name = "member_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private val id: Long,

    @Column(unique = true)
    private val email: String,

    private val password: String,

    private val authority: Authority,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Member
        if (email != other.email) return false

        return true
    }

    override fun hashCode(): Int {
        return email.hashCode()
    }
}

enum class Authority {
    ROLE_USER, ROLE_ADMIN
}