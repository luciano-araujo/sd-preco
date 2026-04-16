package br.edu.ifsp.sd_preco.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record PrecoResponseDTO(
        @Schema(description = "ID do produto", example = "1")
        Long id,

        @Schema(description = "Preço do produto", example = "99.90")
        Double preco) {
}