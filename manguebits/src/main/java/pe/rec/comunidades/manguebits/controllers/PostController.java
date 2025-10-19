package pe.rec.comunidades.manguebits.controllers;

import pe.rec.comunidades.manguebits.dto.postsDTO.PostsDTO;
import pe.rec.comunidades.manguebits.interfaces.services.IPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final IPostService postService;

    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<PostsDTO> criarPost(@RequestBody PostsDTO postsDTO) {
        PostsDTO criado = postService.createPost(postsDTO);
        return ResponseEntity.status(201).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<PostsDTO>> listarPosts() {
        List<PostsDTO> lista = postService.getAllPosts();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostsDTO> buscarPostPorId(@PathVariable Long id) {
        PostsDTO post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostsDTO> atualizarPost(
            @PathVariable Long id,
            @RequestBody PostsDTO postsDTO
    ) {
        PostsDTO atualizado = postService.updatePost(id, postsDTO);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
