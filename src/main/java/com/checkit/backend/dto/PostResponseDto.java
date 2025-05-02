package com.checkit.backend.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class PostResponseDto {
    private Long id;
    private String title;
    private String content;
    private String imageUrl;
    private String nickname;        // 작성자 식별용
    private LocalDateTime createdAt;

}
