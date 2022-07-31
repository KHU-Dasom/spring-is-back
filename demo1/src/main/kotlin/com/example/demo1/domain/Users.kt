package com.example.demo1.domain

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity
data class Users(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,
    var name: String? = null,
    var email: String? = null,
    var pwd: String? = null
) : UserDetails{

    var role = ROLE_TYPE.MEMBER

    override fun getPassword(): String = pwd!!

    override fun getUsername(): String = name!!

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true

    override fun isCredentialsNonExpired(): Boolean = true

    override fun isEnabled(): Boolean = true  // 계정 활성화 비활성화

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> { //관리자인지 게스트인지 권한 만들기
        //GrantedAuthority라는 객체를 가지고 있는 Collection 종류 내보내 주기
        val authorities= mutableListOf<GrantedAuthority>()

        when (role){
            ROLE_TYPE.ADMIN->authorities.add(SimpleGrantedAuthority("ROLE_ADMIN")) // 그냥 ADMIN이 아니라 ROLE_ADMIN을 써야 됨 규칙임
            ROLE_TYPE.MEMBER->authorities.add(SimpleGrantedAuthority("ROLE_MEMBER"))
        }
        return authorities
    }
}