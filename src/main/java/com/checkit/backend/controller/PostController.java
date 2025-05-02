package com.checkit.backend.controller;

import com.checkit.backend.dto.PostResponseDto;
import com.checkit.backend.dto.PostUploadRequest;
import com.checkit.backend.global.response.APIResponse;
import com.checkit.backend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {
    private final PostService postService;

    // 이 API는 multipart/form-data 형식의 요청만 받겠다는 뜻
    @PostMapping(value = "",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public APIResponse<String> uploadPost(
            @ModelAttribute PostUploadRequest request,
            @RequestParam("memberId") Long memberId
    ) {
        try{
            Long PostId = postService.savePost(request, memberId);
            return APIResponse.success("업로드 성공, id:" + PostId);
        }catch(Exception e){
            return APIResponse.fail("게시글 업로드 실패: ", e.getMessage());
        }
    }

    @GetMapping("")
    public APIResponse<List<PostResponseDto>> getAllPost(){
        List<PostResponseDto> posts = postService.getAllPosts();
        return APIResponse.success(posts);
    }

    @GetMapping("/{id}")
    public APIResponse<PostResponseDto> getPost(@PathVariable Long id){
        return APIResponse.success(postService.getPostById(id));
    }

    @PatchMapping("/{id}")
    public APIResponse<?> updatePost(@PathVariable Long id){
        return APIResponse.success("");
    }

    @DeleteMapping("/{id}")
    public APIResponse<String> deletePost(@PathVariable Long id){
        postService.deletePost(id);
        return APIResponse.success("게시글이 삭제되었습니다.");
    }
}
