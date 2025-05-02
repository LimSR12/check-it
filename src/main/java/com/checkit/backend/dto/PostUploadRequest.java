package com.checkit.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
public class PostUploadRequest {
    private String title;
    private String content;
    private MultipartFile[] images;

    public PostUploadRequest() {}  // ModelAttribute에 필요
}
