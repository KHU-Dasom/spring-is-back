package io.dasom.meongdamgil.config.security

import io.dasom.meongdamgil.service.account.AccountDetailsService
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*
import javax.crypto.SecretKey
import javax.servlet.http.HttpServletRequest

@Component
class JwtTokenProvider(
    private val accountDetailsService: AccountDetailsService
) {

    @Value("\${security.jwt.token.secret-key}")
    val secretKey: String = ""

    @Value("\${security.jwt.token.validationTime")
    val validationTime: Long = 0L

    fun createJsonWebToken(email: String): String? {
        val claims = Jwts.claims().setSubject(email)
        claims["email"] = email

        val now = Date()
        return Jwts.builder()
            .setHeaderParam("typ", "JWT")
            .setClaims(claims)
            .setIssuedAt(now)
            .setExpiration(Date(now.time + validationTime))
            .signWith(getSigningKey())
            .compact()
    }

    fun getAuthentication(token: String): Authentication {
        val accountDetails = accountDetailsService.loadUserByUsername(getEmail(token))
        return UsernamePasswordAuthenticationToken(
            accountDetails, "", accountDetails.authorities
        )
    }

    fun getEmail(token: String): String? {
        return Jwts.parserBuilder()
            .setSigningKey(getSigningKey())
            .build()
            .parseClaimsJws(token)
            .body
            .subject
    }

    fun getSigningKey(): SecretKey? {
        return Keys.hmacShaKeyFor(secretKey.toByteArray())
    }

    fun resolveToken(req: HttpServletRequest): String {
        return req.getHeader("Authorization")
    }

    fun validateToken(token: String): Boolean {
        try {
            Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token)
            return true
        } catch (e: JwtException) {
            throw e
        }
    }
}