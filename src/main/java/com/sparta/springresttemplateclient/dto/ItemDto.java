package com.sparta.springresttemplateclient.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

/**
 * ✅ ItemDto 클래스는 클라이언트 애플리케이션에서 아이템 정보를 표현하기 위한 DTO 입니다.
 *
 *    ➡️ JSON 객체를 기반으로 아이템 데이터를 초기화하며, 아이템의 제목과 가격 정보를 포함합니다.
 */
@Getter
@NoArgsConstructor
public class ItemDto {
    private String title; // 아이템의 제목을 나타내는 필드입니다.
    private int price; // 아이템의 가격을 나타내는 필드입니다.

    /**
     * ✅ JSONObject를 기반으로 ItemDto 객체를 생성하는 생성자입니다.
     *
     *    ➡️ JSON 객체에서 아이템의 제목과 가격 정보를 추출하여 필드를 초기화합니다.
     *
     * @param itemJson JSON 형태로 전달된 아이템 데이터입니다.
     */
    public ItemDto(JSONObject itemJson) {
        this.title = itemJson.getString("title"); // JSON 객체에서 "title" 값을 추출하여 필드에 할당합니다.
        this.price = itemJson.getInt("price"); // JSON 객체에서 "price" 값을 추출하여 필드에 할당합니다.
    }
}
