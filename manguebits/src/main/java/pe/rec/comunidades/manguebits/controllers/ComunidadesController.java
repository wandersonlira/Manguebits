package pe.rec.comunidades.manguebits.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.rec.comunidades.manguebits.model.Comunidades;
import pe.rec.comunidades.manguebits.services.ComunidadesService;
import pe.rec.comunidades.manguebits.utils.ErrorResponse;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping(value = {"/api/comunidades"}, consumes = {"application/json"})
public class ComunidadesController {
    private final ComunidadesService service;

    @Autowired
    public ComunidadesController(ComunidadesService service) {
        this.service = service;
    }

    @PostMapping(value = "/v1")
    public ResponseEntity<?> create(@RequestBody Comunidades community) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(
                    this.service.save(community));
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

    @GetMapping(value = {"/v1/"})
    public ResponseEntity<List<Comunidades>> findAll() {
        List<Comunidades> communities = this.service.findAll().stream()
                .map(community -> new Comunidades())
                .collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(communities);
    }

    @PutMapping(value = {"/v1/{id}"})
    public ResponseEntity<?> update(
            @PathVariable(value = "id") Long id,
            @RequestBody Comunidades request) {
        try {
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(
                    this.service.update(id, request)
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
}
