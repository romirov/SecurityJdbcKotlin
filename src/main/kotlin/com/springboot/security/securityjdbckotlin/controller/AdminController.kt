package com.springboot.security.securityjdbckotlin.controller

import com.springboot.security.securityjdbckotlin.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller
class AdminController(
  val userService: UserService
) {
  @GetMapping("/admin")
  fun getAllUser(model: Model): String {
    model.addAttribute("allUsers", userService.findAllUsers());
    return "admin"
  }

  @PostMapping("/admin")
  fun deleteUser(@RequestParam(required = true, defaultValue = "" )userId: Long,
    @RequestParam(required = true, defaultValue = "" ) action: String,
    model: Model): String {
    if (action.equals("delete"))
      userService.deleteUser(userId)
    return "redirect:/admin"
  }
}