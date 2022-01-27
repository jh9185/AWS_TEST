package com.example.springboot.web.controller.Posts;

import com.example.springboot.service.posts.PostsService;
import com.example.springboot.web.dto.Posts.PostsResponseDto;
import com.example.springboot.web.dto.Posts.PostsSaveRequestDto;
import com.example.springboot.web.dto.Posts.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Controller
public class PostsApiController {
    private final PostsService postsService;

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts/posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts/posts-update";
    }

    @GetMapping("/posts/view/{id}")
    public String postsView(@PathVariable Long id, Model model) {
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts/posts-view";
    }

    @PostMapping("/api/v1/posts")
    @ResponseBody
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    @ResponseBody
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }
    @GetMapping("/api/v1/posts/{id}")
    @ResponseBody
    public PostsResponseDto findById(@PathVariable Long id) {
        return postsService.findById(id);
    }


}
