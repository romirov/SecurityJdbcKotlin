//package com.springboot.security.securityjdbckotlin.config
//
//import mu.KLogging
//import org.springframework.context.annotation.Bean
//import org.springframework.context.annotation.Configuration
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
//import org.springframework.security.crypto.password.PasswordEncoder
//
//@Configuration
//class PasswordEncoderAndMatcherConfig {
//  companion object: KLogging()
//  @Bean
//  open fun passwordEncoderAndMatcher(): PasswordEncoder {
//    return object : PasswordEncoder {
//      override fun encode(rawPassword: CharSequence?): String {
//        return BCryptPasswordEncoder().encode(rawPassword)
//      }
//      override fun matches(rawPassword: CharSequence?, encodedPassword: String?): Boolean {
//        logger.info(rawPassword.toString())
//        return BCryptPasswordEncoder().matches(rawPassword, encodedPassword)
//      }
//    }
//  }
//}