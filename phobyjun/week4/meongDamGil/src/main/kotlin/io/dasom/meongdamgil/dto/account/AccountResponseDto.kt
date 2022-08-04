package io.dasom.meongdamgil.dto.account

import io.dasom.meongdamgil.model.Account
import io.dasom.meongdamgil.model.UserRole
import java.time.ZonedDateTime

data class AccountResponseDto(
    val id: Long?,
    val email: String,
    val password: String,
    val userRole: UserRole?,
    val createdAt: ZonedDateTime?,
) {
    constructor(account: Account) : this(
        id = account.id,
        email = account.email,
        password = account.password,
        userRole = account.userRole,
        createdAt = account.createdAt
    )
}