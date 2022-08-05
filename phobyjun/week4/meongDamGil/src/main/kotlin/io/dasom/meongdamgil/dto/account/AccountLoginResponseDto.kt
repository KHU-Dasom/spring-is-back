package io.dasom.meongdamgil.dto.account

import org.springframework.http.HttpStatus

data class AccountLoginResponseDto(
    val httpStatus: HttpStatus,
    val token: String?,
)