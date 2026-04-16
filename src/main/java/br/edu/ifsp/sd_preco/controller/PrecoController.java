package br.edu.ifsp.sd_preco.controller;

import br.edu.ifsp.sd_preco.dto.PrecoResponseDTO;
import br.edu.ifsp.sd_preco.service.PrecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/preco")
@RequiredArgsConstructor
@Tag(name = "Preços", description = "Operações do serviço de preços")
public class PrecoController {

    private final PrecoService precoService;

    @GetMapping("/{id}")
    @Operation(
            summary = "Busca preço por ID",
            description = "Retorna o preço de um produto pelo ID"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Preço encontrado"),
            @ApiResponse(responseCode = "404", description = "Preço não encontrado")
    })
    public ResponseEntity<PrecoResponseDTO> getPreco(@PathVariable Long id) {
        return ResponseEntity.ok(precoService.getPreco(id));
    }

    @PostMapping("/lote")
    @Operation(
            summary = "Busca preços em lote",
            description = "Retorna os preços de uma lista de IDs em uma única requisição"
    )
    @ApiResponse(responseCode = "200", description = "Preços retornados com sucesso")
    public ResponseEntity<Map<Long, Double>> getPrecoLote(@RequestBody List<Long> ids) {
        return ResponseEntity.ok(precoService.getPrecoLote(ids));
    }
}