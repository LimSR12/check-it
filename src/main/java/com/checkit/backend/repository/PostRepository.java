package com.checkit.backend.repository;

import com.checkit.backend.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    // JpaRepository<Post, Long>
    // 제네릭 타입
    // Post : 이 Repository가 관리할 Entity 클래스
    // Long : 그 Entity의 PK


}
