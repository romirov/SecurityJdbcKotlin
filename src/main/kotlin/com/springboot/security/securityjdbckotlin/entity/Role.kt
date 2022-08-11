package com.springboot.security.securityjdbckotlin.entity

import org.springframework.security.core.GrantedAuthority
import javax.persistence.*

@Suppress("JpaAttributeTypeInspection")
@Table(name = "roles")
@Entity(name = "roles")
open class Role (
  open val name: String,
  @Transient
  @ManyToMany(mappedBy = "roles")
  open val users: Set<User>
): GrantedAuthority{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  open val id: Long? = 0L

  override fun getAuthority(): String = name
}