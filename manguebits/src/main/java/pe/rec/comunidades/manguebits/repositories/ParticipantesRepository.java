package pe.rec.comunidades.manguebits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.rec.comunidades.manguebits.model.Participantes;

import java.util.Optional;

public interface ParticipantesRepository extends JpaRepository<Participantes, Long> {
    Optional<Participantes> findByEmail(String email);
    Optional<Participantes> findByCpf(String cpf);
    @Query("SELECT p FROM Participantes p LEFT JOIN FETCH p.comunidades WHERE p.email = :email AND p.senha = :senha")
    Optional<Participantes> findByEmailAndSenhaFetchComunidades(@Param("email") String email, @Param("senha") String senha);
    @Query(value = """ 
    SELECT COUNT(*)
    FROM DBAMV.PARTICIPANTES_COMUNIDADES PC
    INNER JOIN DBAMV.PARTICIPANTES P ON PC.ID_PARTICIPANTE = P.ID_PARTICIPANTE
    INNER JOIN DBAMV.COMUNIDADES C ON PC.ID_COMUNIDADE = C.ID_COMUNIDADE
    WHERE PC.ID_PARTICIPANTE = :idParticipant
    """, nativeQuery = true)
    Long countFollowing(@Param("idParticipant") Long idParticipant);
}
