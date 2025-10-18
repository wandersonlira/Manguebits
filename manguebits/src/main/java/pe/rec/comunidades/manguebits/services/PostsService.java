package pe.rec.comunidades.manguebits.services;

import org.springframework.stereotype.Service;
import pe.rec.comunidades.manguebits.dto.postsDTO.PostDTO;
import pe.rec.comunidades.manguebits.interfaces.services.IPostService;
import pe.rec.comunidades.manguebits.model.Posts;
import pe.rec.comunidades.manguebits.repositories.PostsRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostsService implements IPostService {

    private final PostsRepository repository;

    public PostsService(PostsRepository repository) {
        this.repository = repository;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Posts post = new Posts();
        post.setNome(postDTO.nome());
        post.setIdComunidade(postDTO.idComunidade());
        Posts saved = repository.save(post);
        return mapToDTO(saved);
    }

    @Override
    public PostDTO getPostById(Long id) {
        Optional<Posts> post = repository.findById(id);
        return mapToDTO(post.get());
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO updatePost(Long id, PostDTO postDTO) {
        Optional<Posts> post = repository.findById(id);
        post.get().setNome(postDTO.nome());
        post.get().setCurtidas(postDTO.curtidas());
        post.get().setIdComunidade(postDTO.idComunidade());
        Posts updated = repository.save(post.get());
        return mapToDTO(updated);
    }

    @Override
    public void deletePost(Long id) {
        Optional<Posts> post = this.repository.findById(id);
        repository.delete(post.get());
    }

    private PostDTO mapToDTO(Posts post) {
        return new PostDTO(
                post.getIdPost(),
                post.getNome(),
                post.getCurtidas(),
                post.getIdComunidade(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }
}
