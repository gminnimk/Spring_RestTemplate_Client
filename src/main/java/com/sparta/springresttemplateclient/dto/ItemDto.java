package com.sparta.springresttemplateclient.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * ✅ ItemDto 클래스는 외부 API로부터 받은 데이터를 담기 위한 DTO(Data Transfer Object)입니다.
 */
@Getter
@NoArgsConstructor
public class ItemDto {
    private String title; // 상품의 제목을 나타내는 필드입니다.
    private int price; // 상품의 가격을 나타내는 필드입니다.
}