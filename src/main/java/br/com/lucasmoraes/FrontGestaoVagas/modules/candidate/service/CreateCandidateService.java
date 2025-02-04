package br.com.lucasmoraes.FrontGestaoVagas.modules.candidate.service;

import br.com.lucasmoraes.FrontGestaoVagas.modules.candidate.dto.CreateCandidateDTO;
import lombok.Data;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Data
public class CreateCandidateService {
    public String execute(CreateCandidateDTO candidateDTO) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateCandidateDTO> request = new HttpEntity<>(candidateDTO, headers);

        var url = "http://localhost:8080/candidate";
        var result = restTemplate.postForObject(url, request, String.class);

        return result;
    }
}
