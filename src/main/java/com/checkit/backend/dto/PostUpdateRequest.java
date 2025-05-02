package com.checkit.backend.dto;

import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class PostUpdateRequest {
    private String title;
    private String content;
    private MultipartFile image; // 이미지 교체용 (null이면 기존 유지)
}
