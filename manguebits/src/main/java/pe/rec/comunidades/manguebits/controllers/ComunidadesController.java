package pe.rec.comunidades.manguebits.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.rec.comunidades.manguebits.dto.comunidadesDTO.ComunidadesCreateDTO;
import pe.rec.comunidades.manguebits.dto.comunidadesDTO.ComunidadesUpdateDTO;
import pe.rec.comunidades.manguebits.dto.postsDTO.PostsCreateDTO;
import pe.rec.comunidades.manguebits.model.Comunidades;
import pe.rec.comunidades.manguebits.services.ComunidadesService;
import pe.rec.comunidades.manguebits.utils.ErrorResponse;
import pe.rec.comunidades.manguebits.utils.communitiesUtils.ComunidadesUtils;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/comunidades")
public class ComunidadesController {
    private final ComunidadesService service;

    @Autowired
    public ComunidadesController(ComunidadesService service) {
        this.service = service;
    }

    @PostMapping(value = {"/v1/"}, consumes = "application/json")
    public ResponseEntity<?> create(/*@Valid*/@RequestBody ComunidadesCreateDTO communityDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.service.save(communityDTO).value());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ErrorResponse(e.getMessage()));
        }
    }

    @PostMapping(value = {"/v1/create-posts/{id}"}, consumes = "application/json")
    public ResponseEntity<?> createPostsInComunidades(
            @PathVariable(value = "id") Long id,
            @RequestBody PostsCreateDTO postsCreateDTO) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.service.savePostsInComunidades(postsCreateDTO, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping(value = {"/v1/{id}"})
    public ResponseEntity<?> findById(@PathVariable(value = "id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    this.service.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping(value = {"/v1/fetchPosts/{id}"})
    public ResponseEntity<?> findByIdFetchPosts(@PathVariable(value = "id") Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(
                    this.service.findByIdFetchPosts(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                    new ErrorResponse(e.getMessage()));
        }
    }

    @GetMapping(value = {"/v1"})
    public ResponseEntity<List<Comunidades>> findAll() {
        List<Comunidades> communities = this.service.findAll().stream()
                .map(ComunidadesUtils::mapToComunidades)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(communities);
    }

    @GetMapping(value = {"/v1/fetchPosts"})
    public ResponseEntity<List<Comunidades>> findAllFetchPosts() {
        List<Comunidades> communities = this.service.findAllFetchPosts().stream()
                .map(ComunidadesUtils::mapToComunidadesFetchPosts)
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(communities);
    }

    @PutMapping(value = {"/v1/{id}"}, consumes = "application/json")
    public ResponseEntity<?> update(
            @PathVariable(value = "id") Long id,
            @RequestBody /*@Valid*/ ComunidadesUpdateDTO communityUpdateDTO) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                    this.service.update(id, communityUpdateDTO).value()
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
            this.service.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ErrorResponse(e.getMessage())
            );
        }
    }

    @PostMapping(value = {"/v1/{idComunidade}/participantes/{idParticipante}"}, consumes = "application/json")
    public ResponseEntity<?> adicionarParticipante(
            @PathVariable Long idComunidade,
            @PathVariable Long idParticipante) {
        try {
            HttpStatus status = service.adicionarParticipante(idComunidade, idParticipante);
            return ResponseEntity.status(status).body("Participante adicionado com sucesso à comunidade.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
        }
    }

    @DeleteMapping(value = {"/v1/{idComunidade}/participantes/{idParticipante}"})
    public ResponseEntity<?> removerParticipante(
            @PathVariable Long idComunidade,
            @PathVariable Long idParticipante) {
        try {
            HttpStatus status = service.removerParticipante(idComunidade, idParticipante);
            return ResponseEntity.status(status).body("Participante removido com sucesso da comunidade.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(e.getMessage()));
        }
    }
}
