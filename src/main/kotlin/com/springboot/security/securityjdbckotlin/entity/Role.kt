package com.springboot.security.securityjdbckotlin.entity

import javax.persistence.*

@Suppress("JpaAttributeTypeInspection")
@Table(name = "roles")
@Entity(name = "roles")
data class Role(
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  val id: Long? = null,
  val name: String
)