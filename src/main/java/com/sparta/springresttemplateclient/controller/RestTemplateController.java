package com.sparta.springresttemplateclient.controller;

import com.sparta.springresttemplateclient.dto.ItemDto;
import com.sparta.springresttemplateclient.service.RestTemplateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ✅ RestTemplateController 클래스는 클라이언트 요청을 받아 RESTful API 호출을 처리하는 컨트롤러입니다.
 *
 *    ➡️ RestTemplateService를 통해 외부 API에 대한 GET 및 POST 요청을 수행하며, 그 결과를 클라이언트에게 반환하는 역할을 합니다.
 */
@RestController
@RequestMapping("/api/client") // 모든 요청이 "/api/client" 경로를 기반으로 매핑됩니다.
public class RestTemplateController {

    private final RestTemplateService restTemplateService; // 외부 API 호출 로직을 담당하는 서비스 클래스입니다.

    // RestTemplateService를 주입받는 생성자입니다.
    public RestTemplateController(RestTemplateService restTemplateService) {
        this.restTemplateService = restTemplateService;
    }

    /**
     * ✅ 외부 API에 GET 요청을 보내고, 단일 객체(ItemDto)를 반환합니다.
     *
     * @param query 검색 쿼리 파라미터로, 외부 API 호출 시 사용됩니다.
     * @return ItemDto 외부 API로부터 받은 단일 항목의 데이터가 담긴 객체입니다.
     */
    @GetMapping("/get-call-obj")
    public ItemDto getCallObject(String query) {
        return restTemplateService.getCallObject(query);
    }

    /**
     * ✅ 외부 API에 GET 요청을 보내고, 객체 리스트(List<ItemDto>)를 반환합니다.
     *
     * @return List<ItemDto> 외부 API로부터 받은 여러 항목의 데이터가 담긴 리스트입니다.
     */
    @GetMapping("/get-call-list")
    public List<ItemDto> getCallList() {
        return restTemplateService.getCallList();
    }

    /**
     * ✅ 외부 API에 POST 요청을 보내고, 단일 객체(ItemDto)를 반환합니다.
     *
     * @param query 요청 본문에 포함될 데이터로, 외부 API 호출 시 사용됩니다.
     * @return ItemDto 외부 API로부터 받은 단일 항목의 데이터가 담긴 객체입니다.
     */
    @GetMapping("/post-call")
    public ItemDto postCall(String query) {
        return restTemplateService.postCall(query);
    }

    /**
     * ✅ 외부 API에 HTTP Exchange (POST/GET 등) 요청을 보내고, 객체 리스트(List<ItemDto>)를 반환합니다.
     *
     * @param token 인증 토큰으로, 요청 시 Authorization 헤더에 포함되어 전송됩니다.
     * @return List<ItemDto> 외부 API로부터 받은 여러 항목의 데이터가 담긴 리스트입니다.
     */
    @GetMapping("/exchange-call")
    public List<ItemDto> exchangeCall(@RequestHeader("Authorization") String token) {
        return restTemplateService.exchangeCall(token);
    }
}
