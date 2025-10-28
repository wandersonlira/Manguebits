package pe.rec.comunidades.manguebits.utils.postsUtils;

import pe.rec.comunidades.manguebits.dto.postsDTO.PostsUpdateDTO;
import pe.rec.comunidades.manguebits.model.Posts;

import java.util.Objects;

public class PostsUtils {

    public static boolean checksDataUpdate(Posts posts, PostsUpdateDTO postsUpdateDTO) {
        return !Objects.equals(posts.getConteudo(), postsUpdateDTO.conteudo())
                || !Objects.equals(posts.getCurtidas(), postsUpdateDTO.curtidas());
    }
}
