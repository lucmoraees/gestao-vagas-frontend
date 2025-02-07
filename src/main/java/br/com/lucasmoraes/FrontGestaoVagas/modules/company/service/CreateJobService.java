package br.com.lucasmoraes.FrontGestaoVagas.modules.company.service;

import br.com.lucasmoraes.FrontGestaoVagas.modules.company.dto.CreateJobsDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CreateJobService {
    @Value("host.api.gestao.vagas")
    private String HOST_API_GESTAO_VAGAS;

    public String execute(CreateJobsDTO jobs, String token){
        RestTemplate rt = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);

        HttpEntity<CreateJobsDTO> request = new HttpEntity<>(jobs, headers);

        var url = HOST_API_GESTAO_VAGAS.concat("/company/job/");

        var result = rt.postForObject(url, request, String.class);

        System.out.println(result);

        return result;
    }
}
