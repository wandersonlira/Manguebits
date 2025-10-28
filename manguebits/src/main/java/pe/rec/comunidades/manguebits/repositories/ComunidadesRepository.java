package pe.rec.comunidades.manguebits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.rec.comunidades.manguebits.model.Comunidades;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComunidadesRepository extends JpaRepository<Comunidades, Long> {

    @Query("SELECT C FROM Comunidades C LEFT JOIN FETCH C.posts WHERE C.ID = :id")
    Optional<Comunidades> findByIdFetchPosts(@Param("id") Long id);

    @Query("SELECT DISTINCT C FROM Comunidades C LEFT JOIN FETCH C.posts")
    List<Comunidades> findAllFetchPosts();
}
