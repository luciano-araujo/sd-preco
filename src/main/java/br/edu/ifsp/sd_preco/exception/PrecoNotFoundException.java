package br.edu.ifsp.sd_preco.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // faz o Spring retornar 404 automaticamente
public class PrecoNotFoundException extends RuntimeException {
    public PrecoNotFoundException(Long id) {
        super("Preço não encontrado para o produto id=" + id);
    }
}