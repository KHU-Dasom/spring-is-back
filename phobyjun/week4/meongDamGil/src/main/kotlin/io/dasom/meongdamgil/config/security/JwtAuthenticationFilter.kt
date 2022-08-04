package io.dasom.meongdamgil.config.security

import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.GenericFilterBean
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse
import javax.servlet.http.HttpServletRequest

class JwtAuthenticationFilter(
    private val jwtTokenProvider: JwtTokenProvider
) : GenericFilterBean() {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {
        val token = jwtTokenProvider.resolveToken(request as HttpServletRequest)

        if (jwtTokenProvider.validateToken(token)) {
            val authentication = jwtTokenProvider.getAuthentication(token)
            SecurityContextHolder.getContext().authentication = authentication
        }
        chain.doFilter(request, response)
    }

}