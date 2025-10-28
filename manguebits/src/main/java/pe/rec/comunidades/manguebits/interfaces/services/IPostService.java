package pe.rec.comunidades.manguebits.interfaces.services;

import org.springframework.http.HttpStatus;
import pe.rec.comunidades.manguebits.dto.postsDTO.PostsCreateDTO;
import pe.rec.comunidades.manguebits.dto.postsDTO.PostsUpdateDTO;
import pe.rec.comunidades.manguebits.model.Comunidades;
import pe.rec.comunidades.manguebits.model.Posts;

public interface IPostService {
    void createPost(PostsCreateDTO postDTO, Comunidades community);
    Posts findById(Long id);
    HttpStatus updatePost(Long id, PostsUpdateDTO postsUpdateDTO);
    void deletePost(Long id);
}