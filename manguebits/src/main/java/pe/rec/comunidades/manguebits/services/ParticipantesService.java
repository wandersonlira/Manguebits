package pe.rec.comunidades.manguebits.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.rec.comunidades.manguebits.model.Comunidades;
import pe.rec.comunidades.manguebits.repositories.ComunidadesRepository;
import pe.rec.comunidades.manguebits.repositories.ParticipantesRepository;
import pe.rec.comunidades.manguebits.interfaces.services.IParticipantesService;
import pe.rec.comunidades.manguebits.model.Participantes;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ParticipantesService implements IParticipantesService {

    private final ParticipantesRepository participantesRepository;
    private final ComunidadesRepository comunidadesRepository;


    public ParticipantesService(ParticipantesRepository participantesRepository,
                                ComunidadesRepository comunidadesRepository) {
        this.participantesRepository = participantesRepository;
        this.comunidadesRepository = comunidadesRepository;
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
        return participantesRepository.findByEmailAndSenhaFetchComunidades(email, senha);
    }

    /** Retorna todas as comunidades em que o participante está */
    public List<Comunidades> listarComunidadesDoParticipante(Long idParticipante) {
        Participantes participante = participantesRepository.findById(idParticipante)
                .orElseThrow(() -> new NoSuchElementException("Participante não encontrado."));
        return participante.getComunidades();
    }

    @Transactional
    public HttpStatus adicionarComunidade(Long idParticipante, Long idComunidade) {
        Participantes participante = participantesRepository.findById(idParticipante)
                .orElseThrow(() -> new NoSuchElementException("Participante não encontrado."));

        Comunidades comunidade = comunidadesRepository.findById(idComunidade)
                .orElseThrow(() -> new NoSuchElementException("Comunidade não encontrada."));

        if (!participante.getComunidades().contains(comunidade)) {
            participante.addComunidade(comunidade);
            participantesRepository.save(participante);
        }

        return HttpStatus.OK;
    }

    /** Remove comunidade do participante */
    @Transactional
    public HttpStatus removerComunidade(Long idParticipante, Long idComunidade) {
        Participantes participante = participantesRepository.findById(idParticipante)
                .orElseThrow(() -> new NoSuchElementException("Participante não encontrado."));

        Comunidades comunidade = comunidadesRepository.findById(idComunidade)
                .orElseThrow(() -> new NoSuchElementException("Comunidade não encontrada."));

        participante.removeComunidade(comunidade);
        participantesRepository.save(participante);

        return HttpStatus.NO_CONTENT;
    }

}