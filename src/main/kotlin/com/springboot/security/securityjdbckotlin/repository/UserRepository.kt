package com.springboot.security.securityjdbckotlin.repository

import com.springboot.security.securityjdbckotlin.entity.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
  fun findByUserName(username: String): User?
}