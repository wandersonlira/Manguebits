package pe.rec.comunidades.manguebits.utils.communitiesUtils;

import pe.rec.comunidades.manguebits.model.Comunidades;

import java.util.Objects;

public class ComunidadesUtils {

    public static boolean checksDataUpdate(Comunidades communityUpdated, Comunidades communityDTO) {
        return !Objects.equals(communityUpdated.getNome(), communityDTO.getNome())
                || !Objects.equals(communityUpdated.getDescricao(), communityDTO.getDescricao());
    }
}


