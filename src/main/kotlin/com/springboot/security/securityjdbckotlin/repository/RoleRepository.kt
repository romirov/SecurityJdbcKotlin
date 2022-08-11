package com.springboot.security.securityjdbckotlin.repository;

import com.springboot.security.securityjdbckotlin.entity.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository: JpaRepository<Role, Long> {
  fun findByName(rolename: String): Role
}
