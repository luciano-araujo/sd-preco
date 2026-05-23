package br.edu.ifsp.sd_preco.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Slf4j
@ResponseStatus(HttpStatus.NOT_FOUND) // faz o Spring retornar 404 automaticamente
public class PrecoNotFoundException extends RuntimeException {
    public PrecoNotFoundException(Long id) {
        log.info("Preço não encontrado para produtoId: {}", id);
        super("Preço não encontrado para o produto id=" + id);
    }
}