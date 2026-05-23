package br.edu.ifsp.sd_preco.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record PrecoRequestDTO(

        @Schema(description = "Novo valor do preço", example = "149.90")
        Double valor
) {
}