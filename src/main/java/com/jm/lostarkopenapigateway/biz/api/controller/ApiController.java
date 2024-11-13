package com.jm.lostarkopenapigateway.biz.api.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequiredArgsConstructor
public class ApiController {

    @Value("${spring.data.jwt}")
    private String SECRET_KEY;

    private final RestTemplate restTemplate;

    @GetMapping("/api-test/{name}")
    public ResponseEntity<?> apiTest(@PathVariable String name) {

        String url = "https://developer-lostark.game.onstove.com/characters/"+name+"/siblings";
        // HTTP Headers 설정
        HttpHeaders headers = new HttpHeaders();
        headers.set("accept", "application/json");
        headers.set("authorization", "bearer "+ SECRET_KEY);

        // HttpEntity 객체에 헤더 추가
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // GET 요청 보내기
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        // 응답 반환
        return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
    }


}
