package com.sparta.springresttemplateclient.naver.controller;

import com.sparta.springresttemplateclient.naver.dto.ItemDto;
import com.sparta.springresttemplateclient.naver.service.NaverApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ✅ NaverApiController는 네이버 API와의 통신을 처리하는 REST 컨트롤러입니다.
 *
 *    ➡️ 클라이언트의 검색 요청을 받아 네이버 API를 호출하고, 검색 결과를 반환합니다.
 */
@RestController
@RequestMapping("/api")
public class NaverApiController {

    private final NaverApiService naverApiService;

    public NaverApiController(NaverApiService naverApiService) {
        this.naverApiService = naverApiService;
    }

    /**
     * ✅ 클라이언트로부터 검색 쿼리를 받아 네이버 API를 호출하고, 검색 결과를 반환합니다.
     *
     *    ➡️ 쿼리 파라미터를 이용해 네이버 API에서 아이템을 검색하고, 결과를 List<ItemDto> 형식으로 반환합니다.
     *
     * @param query 검색할 쿼리 파라미터입니다.
     * @return List<ItemDto> 검색 결과로 받은 아이템들의 리스트입니다.
     */
    @GetMapping("/search")
    public List<ItemDto> searchItems(@RequestParam String query) {
        return naverApiService.searchItems(query);
    }
}