package br.com.lucasmoraes.FrontGestaoVagas.modules.candidate.service;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import br.com.lucasmoraes.FrontGestaoVagas.modules.candidate.dto.TokenDTO;

import java.util.HashMap;
import java.util.Map;

@Service
public class CandidateService {

    public TokenDTO login(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(data, headers);

        var url = "http://localhost:8080/candidate/auth";
        var result = restTemplate.postForObject(url, request, TokenDTO.class);

        return result;
    }
}
