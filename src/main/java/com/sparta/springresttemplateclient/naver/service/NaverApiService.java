package com.sparta.springresttemplateclient.naver.service;

import com.sparta.springresttemplateclient.naver.dto.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * ✅ NaverApiService 클래스는 네이버 API와 통신하여 아이템 정보를 검색하고 처리하는 서비스입니다.
 *
 *    ➡️ 이 서비스는 네이버 쇼핑 검색 API를 호출하고, 응답을 `ItemDto` 객체로 변환하여 반환합니다.
 */
@Slf4j(topic = "NAVER API") // Naver API 관련 로그 출력을 위한 Lombok 어노테이션입니다.
@Service // Spring의 서비스 컴포넌트로 등록됩니다.
public class NaverApiService {

    private final RestTemplate restTemplate;

    /**
     * ✅ NaverApiService 클래스의 생성자입니다.
     *
     *    ➡️ RestTemplate을 초기화하여 네이버 API 호출에 사용합니다.
     *
     * @param builder RestTemplateBuilder를 사용하여 RestTemplate을 생성합니다.
     */
    public NaverApiService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    /**
     * ✅ 네이버 API를 호출하여 주어진 쿼리에 대한 아이템 정보를 검색합니다.
     *
     *    ➡️ 네이버 쇼핑 검색 API를 사용하여 쿼리와 일치하는 아이템을 검색하고, 검색 결과를 `ItemDto` 리스트로 반환합니다.
     *
     * @param query 검색할 아이템의 쿼리 문자열입니다.
     * @return List<ItemDto> 검색 결과를 담고 있는 `ItemDto` 객체의 리스트입니다.
     */
    public List<ItemDto> searchItems(String query) {
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
            .fromUriString("https://openapi.naver.com")
            .path("/v1/search/shop.json")
            .queryParam("display", 15) // 한 번에 가져올 아이템의 수
            .queryParam("query", query) // 검색 쿼리
            .encode()
            .build()
            .toUri();
        log.info("uri = " + uri);

        // 네이버 API 호출을 위한 요청 엔티티 생성
        RequestEntity<Void> requestEntity = RequestEntity
            .get(uri)
            .header("X-Naver-Client-Id", "Client-Id") // 네이버 API 클라이언트 ID
            .header("X-Naver-Client-Secret", "Client-Secret") // 네이버 API 클라이언트 시크릿
            .build();

        // 네이버 API 호출 및 응답 처리
        ResponseEntity<String> responseEntity = restTemplate.exchange(requestEntity, String.class);

        log.info("NAVER API Status Code : " + responseEntity.getStatusCode());

        // JSON 응답을 `ItemDto` 리스트로 변환하여 반환
        return fromJSONtoItems(responseEntity.getBody());
    }

    /**
     * ✅ JSON 응답을 `ItemDto` 객체 리스트로 변환합니다.
     *
     *    ➡️ 네이버 API의 JSON 응답에서 아이템 리스트를 추출하여 `ItemDto` 리스트로 변환합니다.
     *
     * @param responseEntity 네이버 API로부터 받은 JSON 응답 문자열입니다.
     * @return List<ItemDto> JSON 응답을 바탕으로 변환된 `ItemDto` 객체의 리스트입니다.
     */
    public List<ItemDto> fromJSONtoItems(String responseEntity) {
        // JSON 문자열을 JSONObject로 변환
        JSONObject jsonObject = new JSONObject(responseEntity);
        // "items" 배열을 추출
        JSONArray items = jsonObject.getJSONArray("items");
        List<ItemDto> itemDtoList = new ArrayList<>();

        // JSONArray를 순회하며 각 아이템을 ItemDto로 변환
        for (Object item : items) {
            ItemDto itemDto = new ItemDto((JSONObject) item);
            itemDtoList.add(itemDto);
        }

        return itemDtoList;
    }
}