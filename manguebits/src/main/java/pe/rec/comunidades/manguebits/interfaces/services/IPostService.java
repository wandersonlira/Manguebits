package pe.rec.comunidades.manguebits.interfaces.services;

import pe.rec.comunidades.manguebits.dto.postsDTO.PostsDTO;
import java.util.List;

public interface IPostService {
    PostsDTO createPost(PostsDTO postDTO);
    PostsDTO getPostById(Long id);
    List<PostsDTO> getAllPosts();
    PostsDTO updatePost(Long id, PostsDTO postsDTO);
    void deletePost(Long id);
}