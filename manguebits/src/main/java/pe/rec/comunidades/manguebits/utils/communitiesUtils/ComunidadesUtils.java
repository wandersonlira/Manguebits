package pe.rec.comunidades.manguebits.utils.communitiesUtils;

import pe.rec.comunidades.manguebits.dto.comunidadesDTO.ComunidadesUpdateDTO;
import pe.rec.comunidades.manguebits.model.Comunidades;

import java.util.Objects;

public class ComunidadesUtils {

    public static boolean checksDataUpdate(Comunidades community, ComunidadesUpdateDTO communityUpdateDTO) {
        return !Objects.equals(community.getNome(), communityUpdateDTO.nome())
                || !Objects.equals(community.getDescricao(), communityUpdateDTO.descricao());
    }

    public static Comunidades mapToComunidades(Comunidades community) {
        return new Comunidades(community.getId(), community.getNome(),
                community.getDescricao(), community.getAdministrador(),
                community.getCategoria(), community.getCreatedAt(), community.getUpdatedAt());
    }
}


