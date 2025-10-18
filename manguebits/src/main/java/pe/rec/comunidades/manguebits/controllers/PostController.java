package pe.rec.comunidades.manguebits.controllers;

import pe.rec.comunidades.manguebits.dto.postsDTO.PostDTO;
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
    public ResponseEntity<PostDTO> criarPost(@RequestBody PostDTO postDTO) {
        PostDTO criado = postService.createPost(postDTO);
        return ResponseEntity.status(201).body(criado);
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> listarPosts() {
        List<PostDTO> lista = postService.getAllPosts();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> buscarPostPorId(@PathVariable Long id) {
        PostDTO post = postService.getPostById(id);
        return ResponseEntity.ok(post);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> atualizarPost(
            @PathVariable Long id,
            @RequestBody PostDTO postDTO
    ) {
        PostDTO atualizado = postService.updatePost(id, postDTO);
        return ResponseEntity.ok(atualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarPost(@PathVariable Long id) {
        postService.deletePost(id);
        return ResponseEntity.noContent().build();
    }
}
