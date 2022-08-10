package com.springboot.security.securityjdbckotlin.entity

import mu.KLogging
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import java.util.stream.Collectors
import javax.persistence.*
import kotlin.collections.HashSet

@Suppress("JpaAttributeTypeInspection")
@Table(name = "users")
@Entity(name = "users")
open class User: UserDetails {
  companion object: KLogging()

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private val id: Long? = null

  @Column(nullable = false)
  private var fistname:String = ""

  @Column(nullable = false)
  private var lastname: String = ""

  @Column(nullable = false)
  private var username: String = ""

  @Column(nullable = false)
  private var email: String = ""

  @Column(nullable = false)
  private var password: String = ""

  @Transient
  private val accountNonExpired: Boolean = true

  @Transient
  private val accountNonLocked: Boolean = true

  @Transient
  private val credentialsNonExpired: Boolean = true

  @Transient
  private val enabled: Boolean = true

//  @ManyToMany
//  @JoinTable(name = "users_roles")
  @OneToMany(fetch = FetchType.EAGER, cascade = arrayOf(CascadeType.ALL))
  @JoinColumn(name = "id",referencedColumnName="id")
  private var roles: Set<Role> = HashSet<Role>()

  override fun getAuthorities(): Collection<GrantedAuthority> {
    return roles
        .stream()
        .map { role ->
          logger.debug("Granting Authority to user with role: " + role.toString())
          SimpleGrantedAuthority(role.toString())
        }
        .collect(Collectors.toSet())
  }

  override fun getPassword(): String {
    return password
  }

  override fun getUsername(): String {
    return username
  }

  override fun isAccountNonExpired(): Boolean {
    return accountNonExpired
  }

  override fun isAccountNonLocked(): Boolean {
    return accountNonLocked
  }

  override fun isCredentialsNonExpired(): Boolean {
    return credentialsNonExpired
  }

  override fun isEnabled(): Boolean {
    return enabled
  }
}