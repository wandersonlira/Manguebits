package pe.rec.comunidades.manguebits.controllers;

import org.springframework.http.HttpStatus;
import pe.rec.comunidades.manguebits.dto.postsDTO.PostsUpdateDTO;
import pe.rec.comunidades.manguebits.interfaces.services.IPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.rec.comunidades.manguebits.utils.ErrorResponse;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final IPostService postService;

    public PostController(IPostService postService) {
        this.postService = postService;
    }

    @GetMapping(value = {"/v1/{id}"})
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    this.postService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ErrorResponse(e.getMessage()));
        }
    }

    @PutMapping(value = {"/v1/{id}"})
    public ResponseEntity<?> updatePost(
            @PathVariable Long id,
            @RequestBody PostsUpdateDTO postsUpdateDTO
    ) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                    this.postService.updatePost(id, postsUpdateDTO).value()
            );
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ErrorResponse(e.getMessage())
            );
        }
    }

    @DeleteMapping(value = {"/v1/{id}"})
    public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
        try {
            this.postService.deletePost(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ErrorResponse(e.getMessage())
            );
        }
    }
}
