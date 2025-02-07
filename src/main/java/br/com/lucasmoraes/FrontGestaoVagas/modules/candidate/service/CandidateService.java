package br.com.lucasmoraes.FrontGestaoVagas.modules.candidate.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import br.com.lucasmoraes.FrontGestaoVagas.modules.candidate.dto.TokenDTO;

import java.util.HashMap;
import java.util.Map;

@Service
public class CandidateService {
    @Value("host.api.gestao.vagas")
    private String HOST_API_GESTAO_VAGAS;

    public TokenDTO login(String username, String password) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(data, headers);

        var url = HOST_API_GESTAO_VAGAS.concat("/candidate/auth");
        var result = restTemplate.postForObject(url, request, TokenDTO.class);

        return result;
    }
}
