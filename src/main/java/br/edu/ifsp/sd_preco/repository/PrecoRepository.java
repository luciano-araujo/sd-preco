package br.edu.ifsp.sd_preco.repository;

import br.edu.ifsp.sd_preco.model.Preco;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

@Repository
public class PrecoRepository {

    private final Map<Long, Preco> precos;

    public PrecoRepository() {
        List<Preco> lista = List.of(
                new Preco(1L,  99.90),
                new Preco(2L,  4999.90),
                new Preco(3L,  249.90),
                new Preco(4L,  599.90),
                new Preco(5L,  1299.90),
                new Preco(6L,  349.90),
                new Preco(7L,  449.90),
                new Preco(8L,  399.90),
                new Preco(9L,  3499.90),
                new Preco(10L, 1899.90),
                new Preco(11L, 599.90),
                new Preco(12L, 799.90),
                new Preco(13L, 1299.90),
                new Preco(14L, 199.90),
                new Preco(15L, 149.90),
                new Preco(16L, 299.90),
                new Preco(17L, 89.90),
                new Preco(18L, 699.90),
                new Preco(19L, 1199.90),
                new Preco(20L, 79.90)
        );

        this.precos = lista.stream()
                .collect(Collectors.toMap(Preco::getProdutoId, Function.identity()));
    }

    public Optional<Preco> findById(Long id) {
        return Optional.ofNullable(precos.get(id));
    }

    public Map<Long, Preco> findAllByIds(List<Long> ids) {
        return ids.stream()
                .filter(precos::containsKey)
                .collect(Collectors.toMap(Function.identity(), precos::get));
    }
}