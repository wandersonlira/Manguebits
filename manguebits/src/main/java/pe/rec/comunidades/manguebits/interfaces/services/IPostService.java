package pe.rec.comunidades.manguebits.interfaces.services;

import pe.rec.comunidades.manguebits.dto.postsDTO.PostDTO;
import java.util.List;

public interface IPostService {
    PostDTO createPost(PostDTO postDTO);
    PostDTO getPostById(Long id);
    List<PostDTO> getAllPosts();
    PostDTO updatePost(Long id, PostDTO postDTO);
    void deletePost(Long id);
}