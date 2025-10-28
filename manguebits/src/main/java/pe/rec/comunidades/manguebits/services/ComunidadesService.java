package pe.rec.comunidades.manguebits.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import pe.rec.comunidades.manguebits.dto.comunidadesDTO.ComunidadesCreateDTO;
import pe.rec.comunidades.manguebits.dto.comunidadesDTO.ComunidadesUpdateDTO;
import pe.rec.comunidades.manguebits.dto.postsDTO.PostsCreateDTO;
import pe.rec.comunidades.manguebits.model.Comunidades;
import pe.rec.comunidades.manguebits.repositories.ComunidadesRepository;
import pe.rec.comunidades.manguebits.utils.communitiesUtils.ComunidadesUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ComunidadesService {
    private final ComunidadesRepository repository;
    private final PostsService postsService;

    @Autowired
    public ComunidadesService(ComunidadesRepository repository, PostsService postsService) {
        this.repository = repository;
        this.postsService = postsService;
    }


    public HttpStatus save(ComunidadesCreateDTO communityDTO) {
        HttpStatus saved = HttpStatus.CREATED;
        Comunidades community = ComunidadesCreateDTO.toEntity(communityDTO);
        try {
            this.repository.save(community);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return saved;
    }

    public Comunidades savePostsInComunidades(PostsCreateDTO postsCreateDTO, Long idCommunity) {
        Comunidades community = this.findById(idCommunity);
        postsService.createPost(postsCreateDTO, community);
        return this.findByIdFetchPosts(idCommunity);
    }

    public List<Comunidades> findAll() {return this.repository.findAll();}

    public List<Comunidades> findAllFetchPosts() {return this.repository.findAllFetchPosts();}

    public Comunidades findById(Long id) {
        Optional<Comunidades> community = this.repository.findById(id).map(ComunidadesUtils::mapToComunidades);
        return community.orElseThrow(() -> new NoSuchElementException("Registro não encontrado!"));
    }

    public Comunidades findByIdFetchPosts(Long id) {
        return repository.findByIdFetchPosts(id)
                .orElseThrow(() -> new RuntimeException("Comunidade não encontrada"));
    }

    public HttpStatus update(Long id, ComunidadesUpdateDTO communityUpdateDTO) {
        HttpStatus updated = HttpStatus.NO_CONTENT;
        Comunidades community = this.findById(id);
        if (ComunidadesUtils.checksDataUpdate(community, communityUpdateDTO)){
            community.setNome(communityUpdateDTO.nome());
            community.setDescricao(communityUpdateDTO.descricao());
            this.repository.save(community);
            updated = HttpStatus.ACCEPTED;
        }
        return updated;
    }

    public void delete(Long id) {
        Comunidades community = this.findById(id);
        this.repository.delete(community);
    }
}

