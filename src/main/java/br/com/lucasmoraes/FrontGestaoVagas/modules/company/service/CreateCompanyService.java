package br.com.lucasmoraes.FrontGestaoVagas.modules.company.service;

import br.com.lucasmoraes.FrontGestaoVagas.modules.company.dto.CreateCompanyDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CreateCompanyService {
    @Value("host.api.gestao.vagas")
    private String HOST_API_GESTAO_VAGAS;

    public String execute(CreateCompanyDTO createCompanyDTO) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<CreateCompanyDTO> request = new HttpEntity<>(createCompanyDTO, headers);

        var url = HOST_API_GESTAO_VAGAS.concat("/company");
        var result = restTemplate.postForObject(url, request, String.class);

        return result;
    }
}
