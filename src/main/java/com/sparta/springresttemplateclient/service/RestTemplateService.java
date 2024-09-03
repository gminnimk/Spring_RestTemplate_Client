package com.sparta.springresttemplateclient.service;

import com.sparta.springresttemplateclient.dto.ItemDto;
import com.sparta.springresttemplateclient.entity.User;
import java.net.URI;
import java.util.ArrayList;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * ✅ RestTemplateService 클래스는 외부 API와의 통신을 처리하는 서비스 클래스입니다.
 *
 *    ➡️ 외부 API와의 GET, POST 요청을 처리하고 그 결과를 반환하는 역할을 합니다.
 */
@Slf4j // 로그 출력을 위한 Lombok 어노테이션입니다.
@Service // Spring의 서비스 컴포넌트로 등록됩니다.
public class RestTemplateService {

    private final RestTemplate restTemplate; // Spring의 RestTemplate을 사용하여 외부 API와 통신합니다.

    /**
     * ✅ RestTemplate의 빌더를 사용하여 RestTemplate 인스턴스를 생성합니다.
     *
     *    ➡️ RestTemplate 인스턴스는 외부 API 호출에 사용됩니다.
     *
     * @param builder RestTemplateBuilder를 통해 RestTemplate을 설정하고 생성합니다.
     */
    public RestTemplateService(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    /**
     * ✅ 외부 API에 GET 요청을 보내고, 단일 객체(ItemDto)를 반환합니다.
     *
     *    ➡️ 외부 API에서 특정 항목을 검색하거나 조회할 때 사용됩니다.
     *
     * @param query 외부 API 호출 시 사용되는 검색 또는 조회 쿼리 파라미터입니다.
     * @return ItemDto 외부 API로부터 받은 단일 항목의 데이터를 담은 객체입니다.
     */
    public ItemDto getCallObject(String query) {
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
            .fromUriString("http://localhost:7070")
            .path("/api/server/get-call-obj")
            .queryParam("query", query)
            .encode()
            .build()
            .toUri();
        log.info("uri = " + uri);

        // 외부 API에 GET 요청을 보내고 응답을 ItemDto 클래스로 매핑합니다.
        ResponseEntity<ItemDto> responseEntity = restTemplate.getForEntity(uri, ItemDto.class);

        log.info("statusCode = " + responseEntity.getStatusCode());

        return responseEntity.getBody(); // 응답 본문에서 ItemDto 객체를 반환합니다.
    }

    /**
     * ✅ 외부 API에 GET 요청을 보내고, 객체 리스트(List<ItemDto>)를 반환합니다.
     *
     *    ➡️ 외부 API에서 여러 항목을 조회할 때 사용됩니다.
     *
     * @return List<ItemDto> 외부 API로부터 받은 여러 항목의 데이터를 담은 리스트입니다.
     */
    public List<ItemDto> getCallList() {
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
            .fromUriString("http://localhost:7070")
            .path("/api/server/get-call-list")
            .encode()
            .build()
            .toUri();
        log.info("uri = " + uri);

        // 외부 API에 GET 요청을 보내고 응답을 String으로 받습니다.
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        log.info("statusCode = " + responseEntity.getStatusCode());
        log.info("Body = " + responseEntity.getBody());

        // JSON 응답 본문을 ItemDto 리스트로 변환하여 반환합니다.
        return fromJSONtoItems(responseEntity.getBody());
    }

    /**
     * ✅ 외부 API에 POST 요청을 보내고, 단일 객체(ItemDto)를 반환합니다.
     *
     *    ➡️ 외부 API에 데이터를 전송하여 새 항목을 생성하거나 등록합니다. 요청 본문에는 사용자 정보가 포함됩니다.
     *
     * @param query 외부 API 호출 시 URL 경로에 포함되는 쿼리 파라미터입니다.
     * @return ItemDto 외부 API로부터 받은 생성된 항목의 데이터를 담은 객체입니다.
     */
    public ItemDto postCall(String query) {
        // 요청 URL 만들기
        URI uri = UriComponentsBuilder
            .fromUriString("http://localhost:7070")
            .path("/api/server/post-call/{query}")
            .encode()
            .build()
            .expand(query)
            .toUri();
        log.info("uri = " + uri);

        // 요청 본문에 포함할 사용자 데이터
        User user = new User("Robbie", "1234");

        // POST 요청을 보내고 응답을 ItemDto 형태로 반환
        ResponseEntity<ItemDto> responseEntity = restTemplate.postForEntity(uri, user, ItemDto.class);

        log.info("statusCode = " + responseEntity.getStatusCode());

        // 응답 본문에서 ItemDto 객체 반환
        return responseEntity.getBody();
    }

    /**
     * ✅ 외부 API에 HTTP Exchange (POST/GET 등) 요청을 보내고, 객체 리스트(List<ItemDto>)를 반환합니다.
     *
     *    ➡️ 이 메서드는 주로 인증이 필요한 요청에서 사용됩니다.
     *
     * @param token 인증 토큰으로, 요청 시 Authorization 헤더에 포함되어 전송됩니다.
     * @return List<ItemDto> 외부 API로부터 받은 여러 항목의 데이터를 담은 리스트입니다.
     */
    public List<ItemDto> exchangeCall(String token) {
        // 인증이 필요한 POST/GET 요청을 보내는 로직이 들어갈 자리입니다.
        return null;
    }

    /**
     * ✅ JSON 응답 문자열을 ItemDto 리스트로 변환합니다.
     *
     *    ➡️ JSON 응답 본문을 파싱하여 ItemDto 객체의 리스트를 생성합니다.
     *
     * @param responseEntity JSON 형태로 된 응답 본문입니다.
     * @return List<ItemDto> JSON 응답에서 변환된 ItemDto 객체 리스트입니다.
     */
    public List<ItemDto> fromJSONtoItems(String responseEntity) {
        // JSON 문자열을 JSONObject로 변환합니다.
        JSONObject jsonObject = new JSONObject(responseEntity);
        // JSON 객체에서 "items" 배열을 추출합니다.
        JSONArray items = jsonObject.getJSONArray("items");
        List<ItemDto> itemDtoList = new ArrayList<>();

        // 배열의 각 요소를 ItemDto 객체로 변환하고 리스트에 추가합니다.
        for (Object item : items) {
            ItemDto itemDto = new ItemDto((JSONObject) item);
            itemDtoList.add(itemDto);
        }

        return itemDtoList; // 변환된 ItemDto 리스트를 반환합니다.
    }
}
