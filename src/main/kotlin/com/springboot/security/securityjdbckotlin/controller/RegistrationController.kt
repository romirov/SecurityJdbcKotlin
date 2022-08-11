package com.springboot.security.securityjdbckotlin.controller

import com.springboot.security.securityjdbckotlin.entity.User
import com.springboot.security.securityjdbckotlin.service.UserService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PostMapping
import javax.validation.Valid

@Controller
class RegistrationController(
  val userService: UserService
) {

  @GetMapping("/registration")
  fun registration(model: Model): String {
    model.addAttribute("userForm", User)
    return "/registration"
  }

  @PostMapping("/registration")
  fun addUser(@ModelAttribute("userForm") @Valid userForm: User, bindingResult: BindingResult, model: Model): String {
    if (bindingResult.hasErrors()) {
      return "registration"
    }
    if (!userForm.getPassword().equals(userForm.passwordConfirm)){
      model.addAttribute("passwordError", "Password not confirmed");
      return "registration";
    }
    if (userService.saveUser(userForm) == null){
      model.addAttribute("usernameError", "A user with the same username already exists");
      return "registration";
    }
    return "redirect:/"
  }
}