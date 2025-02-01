package br.com.lucasmoraes.FrontGestaoVagas.modules.candidate.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProfileuserDTO {
    private String email;
    private UUID id;
    private String description;
    private String username;
    private String name;
}
