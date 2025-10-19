package pe.rec.comunidades.manguebits.services;

import org.springframework.stereotype.Service;
import pe.rec.comunidades.manguebits.repositories.ParticipantesRepository;
import pe.rec.comunidades.manguebits.interfaces.services.IParticipantesService;
import pe.rec.comunidades.manguebits.model.Participantes;

import java.util.Optional;

@Service
public class ParticipantesService implements IParticipantesService {

    private final ParticipantesRepository participantesRepository;

    public ParticipantesService(ParticipantesRepository participantesRepository) {
        this.participantesRepository = participantesRepository;
    }

    @Override
    public Participantes createParticipante(Participantes participante) {
        Optional<Participantes> existenteEmail = participantesRepository.findByEmail(participante.getEmail());
        if (existenteEmail.isPresent()) {
            throw new IllegalArgumentException("Já existe um participante com esse email!");
        }

        if (participante.getCpf() != null && !participante.getCpf().isEmpty()) {
            Optional<Participantes> existenteCpf = participantesRepository.findByCpf(participante.getCpf());
            if (existenteCpf.isPresent()) {
                throw new IllegalArgumentException("Já existe um participante com esse CPF!");
            }
        }

        return participantesRepository.save(participante);
    }

    @Override
    public Optional<Participantes> login(String email, String senha) {
        return participantesRepository.findByEmailAndSenha(email, senha);
    }
}