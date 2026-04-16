package br.edu.ifsp.sd_preco.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Preco {
    private Long produtoId;
    private Double valor;
}