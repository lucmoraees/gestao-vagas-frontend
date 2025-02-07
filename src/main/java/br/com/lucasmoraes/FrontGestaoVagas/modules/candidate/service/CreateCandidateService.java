package br.com.lucasmoraes.FrontGestaoVagas.modules.candidate.service;

import br.com.lucasmoraes.FrontGestaoVagas.modules.candidate.dto.CreateCandidateDTO;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

@Data
public class CreateCandidateService {

    @Value("host.api.gestao.vagas")
    private String HOST_API_GESTAO_VAGAS;

    public String execute(CreateCandidateDTO candidateDTO) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateCandidateDTO> request = new HttpEntity<>(candidateDTO, headers);

        var url = HOST_API_GESTAO_VAGAS.concat("/candidate");
        var result = restTemplate.postForObject(url, request, String.class);

        return result;
    }
}
