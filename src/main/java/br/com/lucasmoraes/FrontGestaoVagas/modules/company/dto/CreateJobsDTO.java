package br.com.lucasmoraes.FrontGestaoVagas.modules.company.dto;

import lombok.Data;

@Data
public class CreateJobsDTO {
    private String description;
    private String benefits;
    private String level;
}
