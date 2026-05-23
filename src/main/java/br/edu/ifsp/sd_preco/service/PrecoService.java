package br.edu.ifsp.sd_preco.service;

import br.edu.ifsp.sd_preco.dto.PrecoResponseDTO;
import br.edu.ifsp.sd_preco.exception.PrecoNotFoundException;
import br.edu.ifsp.sd_preco.model.Preco;
import br.edu.ifsp.sd_preco.repository.PrecoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
}