package pe.rec.comunidades.manguebits.dto.comunidadesDTO;

import pe.rec.comunidades.manguebits.enums.Categoria;

import java.time.LocalDateTime;
import java.util.List;

public record ComunidadesDTO(
        Long id,
        String nome,
        String descricao,
        String administrador,
        Categoria categoria,
        LocalDateTime createdAt,
        LocalDateTime updatedAt,
        List<Long> participantesIds
) {}