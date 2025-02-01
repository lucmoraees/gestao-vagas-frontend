package br.com.lucasmoraes.FrontGestaoVagas.modules.candidate.dto;

import lombok.Data;

import java.util.List;

@Data
public class TokenDTO {
    private String access_token;
    private List<String> roles;
    private Long expires_ind;
}
