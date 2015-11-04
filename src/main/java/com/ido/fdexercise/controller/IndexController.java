package com.ido.fdexercise.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * User: ido
 */
@Controller
@RequestMapping("/")
public class IndexController {

  @RequestMapping
  public String getIndexPage() {
    return "index";
  }
}
