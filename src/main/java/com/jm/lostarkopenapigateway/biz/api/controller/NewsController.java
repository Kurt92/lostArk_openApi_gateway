package com.jm.lostarkopenapigateway.biz.api.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class NewsController {

    @Value("${spring.data.jwt}")
    private String SECRET_KEY;

    private final RestTemplate restTemplate;

    // 진행중인 이벤트 목록을 조회한다
    @GetMapping("/news/events")
    public ResponseEntity<?> findEvent() {

        String url = "https://developer-lostark.game.onstove.com/news/events";
        // HTTP Headers 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("authorization", "bearer "+ SECRET_KEY);

        // HttpEntity 객체에 헤더 추가
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // GET 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }


    // 공지사항을 조회한다.
    @GetMapping("/news/notices")
    public ResponseEntity<?> findNotices() {

        String url = "https://developer-lostark.game.onstove.com/news/notices";
        // HTTP Headers 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("authorization", "bearer "+ SECRET_KEY);

        // HttpEntity 객체에 헤더 추가
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // GET 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }
}
