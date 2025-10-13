package pe.rec.comunidades.manguebits.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pe.rec.comunidades.manguebits.utils.ErrorResponse;
import pe.rec.comunidades.manguebits.dto.participantesDTO.ParticipantesDTO;
import pe.rec.comunidades.manguebits.interfaces.services.IParticipantesService;
import pe.rec.comunidades.manguebits.model.Participantes;

@RestController
@RequestMapping("/manguebits/participantes")
public class ParticipantesController {

    private final IParticipantesService participantesService;

    public ParticipantesController(IParticipantesService participanteService) {
        this.participantesService = participanteService;
    }

    @PostMapping
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
}

