package pe.rec.comunidades.manguebits.interfaces.services;

import org.springframework.http.HttpStatus;
import pe.rec.comunidades.manguebits.dto.comunidadesDTO.ComunidadesDTO;
import pe.rec.comunidades.manguebits.model.Participantes;

import java.util.List;
import java.util.Optional;

public interface IParticipantesService {
    Participantes createParticipante(Participantes participante);

    Optional<Participantes> login(String email, String senha);

    List<ComunidadesDTO> listarComunidadesDoParticipante(Long idParticipante);

    HttpStatus adicionarComunidade(Long idParticipante, Long idComunidade);

    HttpStatus removerComunidade(Long idParticipante, Long idComunidade);
}
