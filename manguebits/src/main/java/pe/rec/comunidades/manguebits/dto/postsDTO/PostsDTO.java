package pe.rec.comunidades.manguebits.dto.postsDTO;

import java.time.LocalDateTime;

public record PostsDTO(
        Long idPost,
        String nome,
        Integer curtidas,
        Integer idComunidade,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
