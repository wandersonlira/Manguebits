package pe.rec.comunidades.manguebits.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.rec.comunidades.manguebits.dto.comunidadesDTO.ComunidadesCreateDTO;
import pe.rec.comunidades.manguebits.dto.comunidadesDTO.ComunidadesUpdateDTO;
import pe.rec.comunidades.manguebits.model.Comunidades;
import pe.rec.comunidades.manguebits.repositories.ComunidadesRepository;
import pe.rec.comunidades.manguebits.utils.communitiesUtils.ComunidadesUtils;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class ComunidadesService {
    private final ComunidadesRepository repository;

    @Autowired
    public ComunidadesService(ComunidadesRepository repository) {this.repository = repository;}


    public Comunidades save(ComunidadesCreateDTO communityDTO) {
        Comunidades community = ComunidadesCreateDTO.toEntity(communityDTO);
        return this.repository.save(community);
    }

    public List<Comunidades> findAll() {return this.repository.findAll();}

    public Comunidades findById(Long id) {
        Optional<Comunidades> community = this.repository.findById(id);
        return community.orElseThrow(() -> new NoSuchElementException("Registro não encontrado!"));
    }

    public boolean update(Long id, ComunidadesUpdateDTO communityUpdateDTO) {
        boolean bool = false;
        Comunidades community = this.findById(id);
        if (ComunidadesUtils.checksDataUpdate(community, communityUpdateDTO)){
            community.setNome(communityUpdateDTO.nome());
            community.setDescricao(communityUpdateDTO.descricao());
            this.repository.save(community);
            bool = true;
        }
        return bool;
    }

    public void delete(Long id) {
        Comunidades community = this.findById(id);
        this.repository.delete(community);
    }
}

