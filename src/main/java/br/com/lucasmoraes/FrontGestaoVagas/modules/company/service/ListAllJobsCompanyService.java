package br.com.lucasmoraes.FrontGestaoVagas.modules.company.service;

import br.com.lucasmoraes.FrontGestaoVagas.modules.candidate.dto.JobDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class ListAllJobsCompanyService {
    @Value("host.api.gestao.vagas")
    private String HOST_API_GESTAO_VAGAS;

    public List<JobDTO> execute(String token) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);

        HttpEntity httpEntity = new HttpEntity<>(headers);

        ParameterizedTypeReference<List<JobDTO>> responseType = new ParameterizedTypeReference<List<JobDTO>>() {};

        var url = HOST_API_GESTAO_VAGAS.concat("/company/job");
        var result = restTemplate.exchange(url, HttpMethod.GET, httpEntity, responseType);
        return result.getBody();
    }
}
