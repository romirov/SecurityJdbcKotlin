package com.springboot.security.securityjdbckotlin.entity

import mu.KLogging
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors
import javax.persistence.*
import javax.validation.constraints.Size

@Suppress("JpaAttributeTypeInspection")
@Table(name = "users")
@Entity(name = "users")
open class User(
  @Column(nullable = false)
  open val fistname:String,

  @Column(nullable = false)
  open val lastname: String,

  @Column(name = "username", nullable = false)
  @Size(min=2, message = "Не меньше 5 знаков")
  open val userName: String,

  @Column(nullable = false)
  open val email: String,

  @Column(name = "password", nullable = false)
  @Size(min=2, message = "Не меньше 5 знаков")
  open val passWord: String,

  @Transient
  open val passwordConfirm: String?,

  @ManyToMany(fetch = FetchType.EAGER)
  open val roles: Set<Role>
): UserDetails {
  companion object: KLogging()

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  open val id: Long? = 0L

  override fun getAuthorities(): Collection<GrantedAuthority> = roles.stream()
        .map { role ->
          logger.debug("Granting Authority to user with role: " + role.toString())
          SimpleGrantedAuthority(role.toString())
        }
        .collect(Collectors.toSet())

  override fun getPassword(): String = passWord

  override fun getUsername(): String = userName

  override fun isAccountNonExpired(): Boolean = true

  override fun isAccountNonLocked(): Boolean = true

  override fun isCredentialsNonExpired(): Boolean = true

  override fun isEnabled(): Boolean = true
}