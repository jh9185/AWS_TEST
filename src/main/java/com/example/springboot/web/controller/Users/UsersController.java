package com.example.springboot.web.controller.Users;

import com.example.springboot.config.auth.LoginUser;
import com.example.springboot.config.auth.dto.SessionUser;
import com.example.springboot.service.users.UsersService;
import com.example.springboot.web.dto.Users.UsersResponseDto;
import com.example.springboot.web.dto.Users.UsersUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class UsersController {
    private final UsersService userService;

    @GetMapping("/user/update/")
    public String userUpdate(Model model, @LoginUser SessionUser user) {
        UsersResponseDto dto = userService.findByEmail(user.getEmail());
        model.addAttribute("loginUser", dto);

        return "profile";
    }

    @PostMapping("/user/update/{id}")
    @ResponseBody
    public Long update(@PathVariable Long id, @RequestBody UsersUpdateRequestDto requestDto) {
        return userService.update(id, requestDto);
    }

}
