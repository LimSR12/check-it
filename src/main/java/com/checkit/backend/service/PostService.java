package com.checkit.backend.service;

import com.checkit.backend.domain.Member;
import com.checkit.backend.domain.Post;
import com.checkit.backend.dto.PostResponseDto;
import com.checkit.backend.dto.PostUploadRequest;
import com.checkit.backend.repository.MemberRepository;
import com.checkit.backend.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;

    private final String uploadDir = System.getProperty("user.dir") + "/uploads/"; // 이미지 저장될 경로

    public Long savePost(PostUploadRequest request, Long memberId) throws IOException {
        File uploadPath = new File(uploadDir);
        if (!uploadPath.exists()) {
            uploadPath.mkdirs(); // 디렉토리가 없으면 생성
        }

        MultipartFile[] images = request.getImages();
        MultipartFile image = images[0];

        String storedFilename = null;
        if (image != null && !image.isEmpty()) {
            if (image.getOriginalFilename() != null && image.getOriginalFilename().contains(".")) {
                // 이미지 파일 확장자 추출
                String ext = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf("."));
                // 이미지 파일명 랜덤 uuid 값을 이용해 생성
                storedFilename = UUID.randomUUID() + ext;
                File dest = new File(uploadDir + storedFilename);
                image.transferTo(dest);
            } else {
                throw new IllegalArgumentException("파일 이름이 유효하지 않습니다.");
            }
        }

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자 정보를 찾을 수 없습니다."));

        Post post = Post.builder()
                .member(member)
                .title(request.getTitle())
                .content(request.getContent())
                .imageUrl(storedFilename != null ? "/images/" + storedFilename : null)
                .build();
        postRepository.save(post);
        return post.getId();
    }

    public List<PostResponseDto> getAllPosts(){
        return postRepository.findAll().stream()
                .map(post -> PostResponseDto.builder()
                        .id(post.getId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .imageUrl(post.getImageUrl())
                        .nickname(post.getMember().getNickname()) // 작성자 정보 일부 포함
                        .createdAt(post.getCreatedAt().toLocalDateTime())
                        .build()
                )
                .collect(Collectors.toList());
    }
}
