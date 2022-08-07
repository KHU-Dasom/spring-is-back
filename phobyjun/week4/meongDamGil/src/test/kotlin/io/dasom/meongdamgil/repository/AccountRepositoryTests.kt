package io.dasom.meongdamgil.repository

import io.dasom.meongdamgil.model.Account
import io.kotest.core.spec.style.BehaviorSpec
import io.mockk.every
import io.mockk.mockk
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class AccountRepositoryTests : BehaviorSpec({

    val accountRepository = mockk<AccountRepository>()

    Given("accountRepository") {
        val account = Account(email = "test@test.com", password = "password")
        accountRepository.saveAndFlush(account)
        When("findAccountByEmail") {
            Then("return Account") {
                every { accountRepository.findAccountByEmail(account.email) } returns account
            }
        }
    }

})
