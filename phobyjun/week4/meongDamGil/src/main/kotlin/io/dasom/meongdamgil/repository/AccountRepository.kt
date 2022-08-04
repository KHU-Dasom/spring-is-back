package io.dasom.meongdamgil.repository

import io.dasom.meongdamgil.model.Account
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, Long> {
    fun findAccountByEmail(email: String): Account?
    fun existsAccountByEmail(email: String): Boolean
}