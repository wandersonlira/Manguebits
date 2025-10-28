package pe.rec.comunidades.manguebits.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pe.rec.comunidades.manguebits.dto.postsDTO.PostsCreateDTO;
import pe.rec.comunidades.manguebits.dto.postsDTO.PostsUpdateDTO;
import pe.rec.comunidades.manguebits.interfaces.services.IPostService;
import pe.rec.comunidades.manguebits.model.Comunidades;
import pe.rec.comunidades.manguebits.model.Posts;
import pe.rec.comunidades.manguebits.repositories.PostsRepository;
import pe.rec.comunidades.manguebits.utils.postsUtils.PostsUtils;

@Service
public class PostsService implements IPostService {
    private final PostsRepository postsRepository;

    @Autowired
    public PostsService(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @Override
    public void createPost(PostsCreateDTO postDTO, Comunidades community) {
        Posts post = PostsCreateDTO.toEntity(postDTO, community);
        postsRepository.save(post);
    }

    @Override
    public Posts findById(Long id) {
        return this.postsRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Nenhum Posts encontrado!"));
    }

    @Override
    public HttpStatus updatePost(Long id, PostsUpdateDTO postsUpdateDTO) {
        HttpStatus updated = HttpStatus.NO_CONTENT;
        Posts post = this.findById(id);
        if (PostsUtils.checksDataUpdate(post, postsUpdateDTO)) {
            post.setConteudo(postsUpdateDTO.conteudo());
            post.setCurtidas(postsUpdateDTO.curtidas());
            this.postsRepository.save(post);
            updated = HttpStatus.ACCEPTED;
        }
        return updated;
    }

    @Override
    public void deletePost(Long id) {
        Posts post = this.findById(id);
        postsRepository.delete(post);
    }

}
