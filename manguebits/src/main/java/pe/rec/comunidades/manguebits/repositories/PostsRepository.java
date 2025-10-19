package pe.rec.comunidades.manguebits.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.rec.comunidades.manguebits.model.Posts;

@Repository
public interface PostsRepository extends JpaRepository<Posts, Long> {}