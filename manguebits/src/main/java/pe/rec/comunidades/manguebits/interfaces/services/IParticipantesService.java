package pe.rec.comunidades.manguebits.interfaces.services;

import pe.rec.comunidades.manguebits.model.Participantes;

import java.util.Optional;

public interface IParticipantesService {
    Participantes createParticipante(Participantes participante);

    Optional<Participantes> login(String email, String senha);
}
