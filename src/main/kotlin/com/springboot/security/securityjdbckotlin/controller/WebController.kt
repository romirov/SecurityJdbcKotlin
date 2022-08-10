package com.springboot.security.securityjdbckotlin.controller

import mu.KLogging
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.servlet.ModelAndView

@Controller
open class WebController {

  @GetMapping("/")
  fun getMainPage(): String {
    val modelAndView = ModelAndView("index")
    modelAndView.status = HttpStatus.OK
    return "redirect:index"
  }

  @GetMapping("/index")
  fun getIndexPage(): String {
    val modelAndView = ModelAndView()
    modelAndView.status = HttpStatus.OK
    modelAndView.addObject("message","WORLD")
    modelAndView.viewName = "index"
    return "index"
  }

  @GetMapping("/login")
  fun login(): String = "login"

  @GetMapping("/logout")
  fun logout(): String = "logout"

  @GetMapping("error")
  fun error(): ModelAndView {
    val modelAndView = ModelAndView("error")
    modelAndView.addObject("BAD REQUEST")
    modelAndView.status = HttpStatus.BAD_REQUEST
    return modelAndView
  }
}