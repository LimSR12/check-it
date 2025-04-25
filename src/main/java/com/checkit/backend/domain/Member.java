package com.checkit.backend.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "member")
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;                    // pk

    @Column(nullable = false, unique = true)
    private String username;    // 로그인용  id

    @Column(nullable = false)
    private String password;    // 로그인용 비밀번호

    @Column(nullable = false, unique = true)
    private String nickname;    // 서비스에 나타나는 회원 닉네임

    @Column(nullable = false)
    private String role;        // ROLE_USER, ROLE_ADMIN 등 권한 구분


}
