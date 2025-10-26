package pe.rec.comunidades.manguebits.dto.postsDTO;

import pe.rec.comunidades.manguebits.model.Posts;

import java.time.LocalDateTime;

public record PostsResponseDTO(
        Long idPost,
        String conteudo,
        Integer curtidas,
        Long idComunidade,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static PostsResponseDTO fromEntity(Posts post) {
        return new PostsResponseDTO(
                post.getIdPost(),
                post.getConteudo(),
                post.getCurtidas(),
                post.getComunidade() != null ? post.getComunidade().getId() : null,
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}
