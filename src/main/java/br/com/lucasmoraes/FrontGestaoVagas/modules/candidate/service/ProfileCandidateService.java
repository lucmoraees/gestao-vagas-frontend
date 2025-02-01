package br.com.lucasmoraes.FrontGestaoVagas.modules.candidate.service;

import br.com.lucasmoraes.FrontGestaoVagas.modules.candidate.dto.ProfileuserDTO;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;

import java.util.Map;

@Service
public class ProfileCandidateService {
    public ProfileuserDTO execute(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(headers);

        var url = "http://localhost:8080/candidate";
        var result = restTemplate.exchange(url, HttpMethod.GET, request, ProfileuserDTO.class);

        return result.getBody();
    }
}
