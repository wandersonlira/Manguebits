package pe.rec.comunidades.manguebits.controllers;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.rec.comunidades.manguebits.dto.LoginDTO;
import pe.rec.comunidades.manguebits.utils.ErrorResponse;
import pe.rec.comunidades.manguebits.dto.participantesDTO.ParticipantesDTO;
import pe.rec.comunidades.manguebits.interfaces.services.IParticipantesService;
import pe.rec.comunidades.manguebits.model.Participantes;

import java.util.Optional;

@RestController
@RequestMapping("/manguebits/participantes")
public class ParticipantesController {

    private final IParticipantesService participantesService;

    public ParticipantesController(IParticipantesService participanteService) {
        this.participantesService = participanteService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> criarParticipante(@RequestBody ParticipantesDTO dto) {
        try {
            Participantes participante = new Participantes();
            participante.setNome(dto.getNome());
            participante.setCpf(dto.getCpf());
            participante.setEmail(dto.getEmail());
            participante.setSenha(dto.getSenha());

            Participantes criado = participantesService.createParticipante(participante);

            // Retorna a entidade criada diretamente
            return ResponseEntity.ok(criado);

        } catch (IllegalArgumentException ex) {
            // Retorna a mensagem de erro personalizada
            return ResponseEntity
                    .status(400)
                    .body(new ErrorResponse(ex.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {
        try {
            Optional<Participantes> participanteOpt = participantesService.login(dto.getEmail(), dto.getSenha());

            if (participanteOpt.isEmpty()) {
                return ResponseEntity.status(401).body(new ErrorResponse("Credenciais inválidas"));
            }

            return ResponseEntity.ok(participanteOpt.get());

        } catch (Exception ex) {
            return ResponseEntity.status(500).body(new ErrorResponse("Erro no servidor"));
        }
    }
}

