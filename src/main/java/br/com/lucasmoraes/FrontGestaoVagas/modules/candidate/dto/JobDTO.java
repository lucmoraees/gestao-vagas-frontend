package br.com.lucasmoraes.FrontGestaoVagas.modules.candidate.dto;

import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Data
public class JobDTO {
    private UUID id;
    private String description;
    private String benefits;
    private String level;
    private UUID companyId;
    private Date createdAt;
}
