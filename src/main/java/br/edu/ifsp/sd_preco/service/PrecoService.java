package br.edu.ifsp.sd_preco.service;

import br.edu.ifsp.sd_preco.dto.PrecoRequestDTO;
import br.edu.ifsp.sd_preco.dto.PrecoResponseDTO;
import br.edu.ifsp.sd_preco.exception.PrecoNotFoundException;
import br.edu.ifsp.sd_preco.model.Preco;
import br.edu.ifsp.sd_preco.repository.PrecoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class PrecoService {

    private final PrecoRepository precoRepository;

    public PrecoResponseDTO getPreco(Long id) {
        log.info("Consultando preço para produtoId: {}", id);
        Preco preco = precoRepository.findById(id)
                .orElseThrow(() -> new PrecoNotFoundException(id));

        log.info("Preço encontrado para produtoId: {}: {}", id, preco.getValor());
        return new PrecoResponseDTO(preco.getProdutoId(), preco.getValor());
    }

    public Map<Long, Double> getPrecoLote(List<Long> ids) {
        log.info("Consultando preços para os IDs: {}", ids);
        return precoRepository.findAllByIds(ids)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().getValor()
                ));
    }

    public void criarPreco(Long id, PrecoRequestDTO request) {
        log.info("Cadastrando novo preço originário do catálogo para o produtoId: {}", id);

        // Validações da Camada de Serviço (Item 2 da sua descrição)
        if (request == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Corpo da requisição não pode ser nulo");
        }

        if (request.valor() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O campo 'valor' é obrigatório");
        }

        if (request.valor() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Não é possível cadastrar preço negativo");
        }

        // Criando nova entidade via gestão contínua (Item 3 da sua descrição)
        Preco preco = new Preco(id, request.valor());

        // O repositório já tratará isso como um novo map associado
        precoRepository.save(preco);

        log.info("Novo Preço cadastrado no mapa em memória para produtoId: {}", id);
    }

    public PrecoResponseDTO atualizarPreco(Long id, PrecoRequestDTO request) {
        log.info("Atualizando preço do produtoId: {}", id);

        if (request == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Corpo da requisição não pode ser nulo");
        }

        if (request.valor() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O campo 'valor' é obrigatório");
        }

        if (request.valor() < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O valor não pode ser negativo");
        }

        Preco preco = precoRepository.findById(id)
                .orElseThrow(() -> new PrecoNotFoundException(id));

        preco.setValor(request.valor());
        precoRepository.save(preco);

        log.info("Preço atualizado para produtoId: {}: {}", id, preco.getValor());
        return new PrecoResponseDTO(preco.getProdutoId(), preco.getValor());
    }

    public void deletarPreco(Long id) {
        log.info("Removendo preço do produtoId: {}", id);

        if (precoRepository.findById(id).isEmpty()) {
            throw new PrecoNotFoundException(id);
        }

        precoRepository.deleteById(id);
        log.info("Preço removido para produtoId: {}", id);
    }
}