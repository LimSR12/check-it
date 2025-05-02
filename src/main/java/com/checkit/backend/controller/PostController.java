package com.checkit.backend.controller;

import com.checkit.backend.global.response.APIResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    @PostMapping("")
    public APIResponse<String> uploadPost(){
        return APIResponse.success("업로드 성공");
    }

    @GetMapping("")
    public APIResponse<?> getAllPost(){
        return APIResponse.success("");
    }

    @GetMapping("/{id}")
    public APIResponse<?> getPost(){
        return APIResponse.success("");
    }

    @PatchMapping("/{id}")
    public APIResponse<?> updatePost(){
        return APIResponse.success("");
    }

    @DeleteMapping("/{id}")
    public APIResponse<?> deletePost(){
        return APIResponse.success("");
    }
}
