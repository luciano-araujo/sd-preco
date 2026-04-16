package br.edu.ifsp.sd_preco.service;

import br.edu.ifsp.sd_preco.dto.PrecoResponseDTO;
import br.edu.ifsp.sd_preco.model.Preco;
import br.edu.ifsp.sd_preco.repository.PrecoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PrecoService {

    private final PrecoRepository precoRepository;

    public PrecoResponseDTO getPreco(Long id) {
        Preco preco = precoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Preço não encontrado para o produto id=" + id));

        return new PrecoResponseDTO(preco.getProdutoId(), preco.getValor());
    }

    public Map<Long, Double> getPrecoLote(List<Long> ids) {
        return precoRepository.findAllByIds(ids)
                .entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        entry -> entry.getValue().getValor()
                ));
    }
}