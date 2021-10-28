package ru.event.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.event.dto.UserDto;
import ru.event.entity.User;

import java.util.HashMap;

@Controller
public class MainController {
  @GetMapping("/")
  public String main(Model model, @AuthenticationPrincipal User authUser) {
    HashMap<Object, Object> data = new HashMap<>();
    if (authUser != null) {
      data.put("authUser", UserDto.userDtoMapper(authUser));
    }
    model.addAttribute("frontendData", data);
    return "index";
  }
}
