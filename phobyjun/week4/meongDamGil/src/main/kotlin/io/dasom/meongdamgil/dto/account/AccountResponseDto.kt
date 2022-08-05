package io.dasom.meongdamgil.dto.account

import io.dasom.meongdamgil.model.Account
import io.dasom.meongdamgil.model.UserRole
import java.time.ZonedDateTime

data class AccountResponseDto(
    val email: String,
    val userRole: UserRole?,
    val createdAt: ZonedDateTime?,
) {
    constructor(account: Account) : this(
        email = account.email,
        userRole = account.userRole,
        createdAt = account.createdAt
    )
}