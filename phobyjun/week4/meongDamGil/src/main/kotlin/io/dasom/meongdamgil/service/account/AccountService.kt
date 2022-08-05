package io.dasom.meongdamgil.service.account

import io.dasom.meongdamgil.config.security.JwtTokenProvider
import io.dasom.meongdamgil.dto.account.AccountLoginResponseDto
import io.dasom.meongdamgil.dto.account.AccountRequestDto
import io.dasom.meongdamgil.dto.account.AccountResponseDto
import io.dasom.meongdamgil.model.Account
import io.dasom.meongdamgil.repository.AccountRepository
import lombok.RequiredArgsConstructor
import org.springframework.http.HttpStatus
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.web.server.ResponseStatusException
import javax.transaction.Transactional

@Service
@RequiredArgsConstructor
class AccountService(
    private val accountRepository: AccountRepository,
    private val jwtTokenProvider: JwtTokenProvider,
    private val passwordEncoder: PasswordEncoder
) {

    fun findAccount(email: String): Account {
        return accountRepository.findAccountByEmail(email)
            ?: throw ResponseStatusException(HttpStatus.NOT_FOUND, "No Account.")
    }

    fun existsAccount(email: String): Boolean {
        return accountRepository.existsAccountByEmail(email)
    }

    fun validateAccount(reqPw: String, accountPw: String): Boolean {
        return passwordEncoder.matches(reqPw, accountPw)
    }

    @Transactional
    fun createAccount(requestDto: AccountRequestDto): AccountResponseDto {
        val account = accountRepository.save(
            Account(
                email = requestDto.email,
                password = passwordEncoder.encode(requestDto.password)
            )
        )
        return AccountResponseDto(account)
    }

    @Transactional
    fun loginAccount(requestDto: AccountRequestDto): AccountLoginResponseDto {
        return AccountLoginResponseDto(
            HttpStatus.OK,
            jwtTokenProvider.createJsonWebToken(requestDto.email)
        )
    }
}