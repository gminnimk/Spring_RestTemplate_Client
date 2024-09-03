package com.sparta.springresttemplateclient.service;

import com.sparta.springresttemplateclient.dto.ItemDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ✅ RestTemplateService 클래스는 외부 API와의 통신을 처리하는 서비스 클래스입니다.
 *
 *    ➡️ 외부 API와의 GET, POST 요청을 처리하고 그 결과를 반환하는 역할을 합니다.
 */
@Slf4j // 로그 출력을 위한 Lombok 어노테이션입니다.
@Service // Spring의 서비스 컴포넌트로 등록됩니다.
public class RestTemplateService {

    /**
     * ✅ 외부 API에 GET 요청을 보내고, 단일 객체(ItemDto)를 반환합니다.
     *
     *    ➡️ 외부 API에서 특정 항목을 검색하거나 조회할 때 사용됩니다.
     *
     * @param query 외부 API 호출 시 사용되는 검색 또는 조회 쿼리 파라미터입니다.
     * @return ItemDto 외부 API로부터 받은 단일 항목의 데이터를 담은 객체입니다.
     */
    public ItemDto getCallObject(String query) {
        // 실제 외부 API 호출 로직이 들어갈 자리입니다.
        return null;
    }

    /**
     * ✅ 외부 API에 GET 요청을 보내고, 객체 리스트(List<ItemDto>)를 반환합니다.
     *
     *    ➡️ 외부 API에서 여러 항목을 조회할 때 사용됩니다.
     *
     * @return List<ItemDto> 외부 API로부터 받은 여러 항목의 데이터를 담은 리스트입니다.
     */
    public List<ItemDto> getCallList() {
        // 실제 외부 API 호출 로직이 들어갈 자리입니다.
        return null;
    }

    /**
     * ✅ 외부 API에 POST 요청을 보내고, 단일 객체(ItemDto)를 반환합니다.
     *
     *    ➡️ 외부 API에 데이터를 생성하거나 등록할 때 사용됩니다.
     *
     * @param query 외부 API 호출 시 사용되는 데이터 또는 요청 본문입니다.
     * @return ItemDto 외부 API로부터 받은 생성된 항목의 데이터를 담은 객체입니다.
     */
    public ItemDto postCall(String query) {
        // 실제 외부 API 호출 로직이 들어갈 자리입니다.
        return null;
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
        // 실제 외부 API 호출 로직이 들어갈 자리입니다.
        return null;
    }
}