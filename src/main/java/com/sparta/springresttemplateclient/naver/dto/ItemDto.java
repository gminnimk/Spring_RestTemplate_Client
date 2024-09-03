package com.sparta.springresttemplateclient.naver.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.json.JSONObject;

/**
 * ✅ ItemDto 클래스는 네이버 API로부터 반환된 아이템 정보를 담는 DTO 입니다.
 *
 *    ➡️ 이 클래스는 네이버 API에서 제공하는 아이템의 주요 속성을 포함하며, JSON 객체를 통해 초기화할 수 있습니다.
 */
@Getter
@NoArgsConstructor
public class ItemDto {

    // 아이템의 제목입니다.
    private String title;

    // 아이템의 링크 URL입니다.
    private String link;

    // 아이템의 이미지 URL입니다.
    private String image;

    // 아이템의 최저 가격입니다.
    private int lprice;

    /**
     * ✅ JSON 객체를 통해 ItemDto 객체를 초기화합니다.
     *
     *    ➡️ JSON 객체에서 아이템의 각 속성(제목, 링크, 이미지, 가격)을 추출하여 ItemDto의 필드에 설정합니다.
     *
     * @param itemJson JSON 객체로부터 아이템 정보를 추출하는 데 사용됩니다.
     */
    public ItemDto(JSONObject itemJson) {
        this.title = itemJson.getString("title");
        this.link = itemJson.getString("link");
        this.image = itemJson.getString("image");
        this.lprice = itemJson.getInt("lprice");
    }
}
