package pe.rec.comunidades.manguebits.services;

import pe.rec.comunidades.manguebits.dto.postsDTO.PostDTO;
import pe.rec.comunidades.manguebits.model.Post;
import pe.rec.comunidades.manguebits.repository.PostRepository;
import java.util.List;
import java.util.stream.Collectors;

public class PostServiceImpl implements IPostService {

    private final PostRepository repository;

    public PostServiceImpl(PostRepository repository) {
        this.repository = repository;
    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = new Post();
        post.setNome(postDTO.nome());
        post.setIdComunidade(postDTO.idComunidade());
        Post saved = repository.save(post);
        return mapToDTO(saved);
    }

    @Override
    public PostDTO getPostById(Long id) {
        Post post = repository.findById(id);
        return mapToDTO(post);
    }

    @Override
    public List<PostDTO> getAllPosts() {
        return repository.findAll().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO updatePost(Long id, PostDTO postDTO) {
        Post post = repository.findById(id);
        post.setNome(postDTO.nome());
        post.setCurtidas(postDTO.curtidas());
        post.setIdComunidade(postDTO.idComunidade());
        Post updated = repository.update(post);
        return mapToDTO(updated);
    }

    @Override
    public void deletePost(Long id) {
        repository.delete(id);
    }

    private PostDTO mapToDTO(Post post) {
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
