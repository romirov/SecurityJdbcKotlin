package com.springboot.security.securityjdbckotlin.service

import com.springboot.security.securityjdbckotlin.entity.Role
import com.springboot.security.securityjdbckotlin.entity.User
import com.springboot.security.securityjdbckotlin.repository.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.security.crypto.password.PasswordEncoder
import java.util.*

class UserService(
  private val userRepository: UserRepository,
  private val passwordEncoder: PasswordEncoder
  ) : UserDetailsService {

  override fun loadUserByUsername(username: String): UserDetails {
    return userRepository?.findByUserName(username)?: throw UsernameNotFoundException("User not found")
  }

  fun findUserById(id: Long): User? = userRepository.findByIdOrNull(id)

  fun findUserByUsername(username: String): User? = userRepository.findByUserName(username)

  fun findAllUsers(): List<User> = userRepository.findAll()

  fun saveUser(user: User): User? {
    val userFromDb = userRepository.findByUserName(user.userName)

    if(userFromDb == null) return userFromDb

    val userForSaving = User(
        user.fistname,
        user.lastname,
        user.userName,
        user.email,
        passwordEncoder.encode(user.passWord),
        user.passwordConfirm,
        Collections.singleton(Role( "USER", Collections.singleton(user)))
    )
    return userRepository.save(user)
  }

  fun deleteUser(id: Long): Boolean {
    if(userRepository.existsById(id)) {
      userRepository.deleteById(id)
      return true
    }
    return false
  }
}