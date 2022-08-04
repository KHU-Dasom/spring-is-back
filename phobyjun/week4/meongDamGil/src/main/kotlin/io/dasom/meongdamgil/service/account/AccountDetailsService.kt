package io.dasom.meongdamgil.service.account

import io.dasom.meongdamgil.repository.AccountRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class AccountDetailsService(
    private val accountRepository: AccountRepository
) : UserDetailsService {
    override fun loadUserByUsername(email: String?): UserDetails {
        val account = email?.let { accountRepository.findAccountByEmail(it) }
            ?: throw UsernameNotFoundException("Not found: $email")

        return org.springframework.security.core.userdetails.User
            .withUsername(email)
            .password(account.password)
            .authorities(account.userRole!!.name)
            .accountExpired(false)
            .accountLocked(false)
            .credentialsExpired(false)
            .disabled(false)
            .build()
    }
}