package io.dasom.meongdamgil.controller

import io.dasom.meongdamgil.dto.account.AccountLoginResponseDto
import io.dasom.meongdamgil.dto.account.AccountRequestDto
import io.dasom.meongdamgil.dto.account.AccountResponseDto
import io.dasom.meongdamgil.service.account.AccountService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
class AccountController(
    private val accountService: AccountService
) {

    @PostMapping("/account/new")
    fun newAccount(
        @RequestBody accountReq: AccountRequestDto
    ): ResponseEntity<AccountResponseDto> {
        if (accountService.existsAccount(accountReq.email)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Email Already Exists.")
        }
        return ResponseEntity.ok(accountService.createAccount(accountReq))
    }

    @PostMapping("/account/login")
    fun loginAccount(
        @RequestBody accountReq: AccountRequestDto
    ): ResponseEntity<AccountLoginResponseDto> {
        if (!accountService.existsAccount(accountReq.email)) {
            throw ResponseStatusException(HttpStatus.NOT_FOUND, "Not Found Account.")
        }

        val account = accountService.findAccount(accountReq.email)

        if (!accountService.validateAccount(accountReq.password, account.password)) {
            throw ResponseStatusException(HttpStatus.BAD_REQUEST, "Invalid Password.")
        }

        return ResponseEntity.ok(accountService.loginAccount(accountReq))
    }
}