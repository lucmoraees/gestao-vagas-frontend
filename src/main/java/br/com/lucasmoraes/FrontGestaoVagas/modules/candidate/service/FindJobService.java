package br.com.lucasmoraes.FrontGestaoVagas.modules.candidate.service;

import br.com.lucasmoraes.FrontGestaoVagas.modules.candidate.dto.JobDTO;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Service
public class FindJobService {
    public List<JobDTO> execute(String token, String filter) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity<Map<String, String>> request = new HttpEntity<>(headers);

        var url = "http://localhost:8080/candidate/job";
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url).queryParam("filter", filter);

        ParameterizedTypeReference<List<JobDTO>> responseType = new ParameterizedTypeReference<List<JobDTO>>() {};

        var result = restTemplate.exchange(builder.toUriString(), HttpMethod.GET, request, responseType);

        return result.getBody();
    }
}
