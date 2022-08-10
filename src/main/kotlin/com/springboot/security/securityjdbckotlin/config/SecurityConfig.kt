package com.springboot.security.securityjdbckotlin.config

import com.springboot.security.securityjdbckotlin.service.CustomUserDetailsService
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
class SecurityConfig(
  private val customUserDetailsService: CustomUserDetailsService//,

) {
  fun configure(auth: AuthenticationManagerBuilder) {
    auth.userDetailsService(customUserDetailsService)
        .passwordEncoder(passwordEncoder())
  }
  @Bean
  fun passwordEncoder(): PasswordEncoder {
    return BCryptPasswordEncoder()
  }
  @Throws(Exception::class)
  fun configure(http: HttpSecurity) {
    // Set session management to stateless
    http.csrf().disable()
    http
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .formLogin()
        .and()
        .rememberMe()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .logout()
        .logoutUrl("/logout")
        .logoutSuccessUrl("/login")
        .deleteCookies("remember-me")
  }
}