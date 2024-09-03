package com.sparta.springresttemplateclient.entity;

import lombok.Getter;

/**
 * ✅ User 클래스는 애플리케이션 내에서 사용자 정보를 저장하기 위한 엔티티(Entity) 클래스입니다.
 *
 *    ➡️ 주로 사용자 인증 및 권한 관리를 위해 사용됩니다.
 */
@Getter
public class User {
    private String username; // 사용자의 이름(또는 아이디)을 나타내는 필드입니다.
    private String password; // 사용자의 비밀번호를 나타내는 필드입니다.

    /**
     * ✅ User 클래스의 생성자입니다.
     *
     *    ➡️ 사용자의 이름과 비밀번호를 인자로 받아 해당 필드들을 초기화합니다.
     *
     * @param username 사용자의 이름 또는 아이디.
     * @param password 사용자의 비밀번호.
     */
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}