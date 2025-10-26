package pe.rec.comunidades.manguebits.dto.postsDTO;

import java.time.LocalDateTime;

public record PostsDTO(
        String conteudo,
        Integer curtidas,
        Integer idComunidade,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
