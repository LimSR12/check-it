package com.checkit.backend.service;

import com.checkit.backend.domain.Post;
import com.checkit.backend.dto.PostUploadRequest;
import com.checkit.backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final String uploadDir = "uploads/";    // 이미지 저장될 경로

    public Long savePost(PostUploadRequest request ) throws IOException {

        MultipartFile[] images = request.getImages();
        MultipartFile image = images[0];

        String storedFilename = null;
        if (image != null && !image.isEmpty()) {
            if (image.getOriginalFilename() != null && image.getOriginalFilename().contains(".")) {
                // 이미지 파일 확장자 추출
                String ext = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
                // 이미지 파일명 랜덤 uuid 값을 이용해 생성
                storedFilename = UUID.randomUUID() + ext;
                File uploadPath = new File(uploadDir + storedFilename);
                image.transferTo(uploadPath);
            } else {
                throw new IllegalArgumentException("파일 이름이 유효하지 않습니다.");
            }
        }

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .imageUrl(storedFilename != null ? "/images/" + storedFilename : null)
                .build();
        postRepository.save(post);
        return post.getId();
    }
}
