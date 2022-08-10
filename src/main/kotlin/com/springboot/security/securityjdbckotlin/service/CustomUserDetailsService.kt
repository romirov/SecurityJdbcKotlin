package com.springboot.security.securityjdbckotlin.service

import com.springboot.security.securityjdbckotlin.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val userRepository: UserRepository) : UserDetailsService {
  override fun loadUserByUsername(username: String): UserDetails {
    return userRepository.findOneByUsername(username)!!
  }
}