package com.beatriz.petstore;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class FirstController {

  @GetMapping
  public String hello() {
    return "Hello, World!";
  }

  @GetMapping("/bye")
  public String bye(@RequestParam(defaultValue = "world", required = false) String name){
    return "Goodbye, " + name + " World!";
  }
}
