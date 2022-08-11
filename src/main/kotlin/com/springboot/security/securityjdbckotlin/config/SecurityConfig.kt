package com.springboot.security.securityjdbckotlin.config

import com.springboot.security.securityjdbckotlin.repository.UserRepository
import com.springboot.security.securityjdbckotlin.service.UserService
import org.springframework.context.annotation.Bean
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder

@EnableWebSecurity
class SecurityConfig(
  private val userRepository: UserRepository
): WebSecurityConfigurerAdapter() {

  override fun configure(auth: AuthenticationManagerBuilder) {
    auth.userDetailsService(getUserService())
        .passwordEncoder(passwordEncoder())
  }

  @Bean
  fun passwordEncoder(): PasswordEncoder {
    return BCryptPasswordEncoder()
  }

  @Bean
  fun getUserService(): UserService {
    return UserService(userRepository, passwordEncoder())
  }

  @Throws(Exception::class)
  override fun configure(http: HttpSecurity) {
    // Set session management to stateless
    http.csrf().disable()
        .authorizeRequests()
            //Доступ только для не зарегистрированных пользователей
            .antMatchers("/registration").not().fullyAuthenticated()
            //Доступ только для пользователей с ролью Администратор
            .antMatchers("/admin/**").hasRole("ADMIN")
            .antMatchers("/news").hasRole("USER")
            //Доступ разрешен всем пользователей
            .antMatchers("/", "/resources/**").permitAll()
            .anyRequest().authenticated()
        .and()
            .formLogin()
            //.loginPage("/login")
            //.defaultSuccessUrl("/")
            //.permitAll()
        .and()
            .rememberMe()
        .and()
            .sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
            .logout()
            .permitAll()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/")
            .deleteCookies("remember-me")
  }

//  override fun configure(webSecurity: WebSecurity) {
//    webSecurity.ignoring().antMatchers("/", "/resources/**")
//  }
}