package com.example.springboot.web.controller;

import com.example.springboot.config.auth.LoginUser;
import com.example.springboot.config.auth.dto.SessionUser;
import com.example.springboot.service.bus.BusService;
import com.example.springboot.service.posts.PostsService;
import com.example.springboot.service.users.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class IndexController {
    private final UsersService userService;
    private final PostsService postsService;
    private final BusService busService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String index(Model model, @LoginUser SessionUser user) {
        model.addAttribute("posts", postsService.findAllDesc());
        model.addAttribute("buslist", busService.findAllDesc());

        if (user != null) {
            model.addAttribute("loginUserName", user.getName());

            if(user.getRegion() != "") {
                model.addAttribute("loginUserRegion", user.getRegion());
            }
        }
        return "index";
    }
}

