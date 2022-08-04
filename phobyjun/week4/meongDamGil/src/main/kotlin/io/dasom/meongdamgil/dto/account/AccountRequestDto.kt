package io.dasom.meongdamgil.dto.account

import io.dasom.meongdamgil.model.Account
import io.dasom.meongdamgil.model.UserRole

data class AccountRequestDto(
    val email: String,
    val password: String,
    val userRole: UserRole? = UserRole.ROLE_USER,
) {
    fun toEntity(): Account = Account(
        email = email,
        password = password,
        userRole = userRole
    )
}