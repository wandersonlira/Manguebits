package pe.rec.comunidades.manguebits.dto.postsDTO;

import pe.rec.comunidades.manguebits.model.Comunidades;
import pe.rec.comunidades.manguebits.model.Posts;

public record PostsCreateDTO(
        String conteudo
) {
    public static Posts toEntity(PostsCreateDTO postsCreateDTO, Comunidades community) {
        Posts post = new Posts();
        post.setConteudo(postsCreateDTO.conteudo());
        post.setCurtidas(0);
        post.setComunidade(community);
        return post;
    }
}
