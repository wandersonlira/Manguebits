package pe.rec.comunidades.manguebits.dto.comunidadesDTO;

import jakarta.validation.constraints.NotBlank;
import pe.rec.comunidades.manguebits.enums.Categoria;
import pe.rec.comunidades.manguebits.model.Comunidades;


public record ComunidadesCreateDTO(
        @NotBlank(message = "Atributo <conteudo> é obrigatório!") String nome,
        @NotBlank(message = "Atributo <descricao> é obrigatório!") String descricao,
        @NotBlank(message = "Atributo <administrador> é obrigatório!") String administrador,
        @NotBlank(message = "Atributo <categoria> é obrigatório!") Categoria categoria
) {
    public static Comunidades toEntity(ComunidadesCreateDTO requestDTO) {
        Comunidades community = new Comunidades();
        community.setNome(requestDTO.nome());
        community.setDescricao(requestDTO.descricao());
        community.setAdministrador(requestDTO.administrador());
        community.setCategoria(requestDTO.categoria());
        return community;
    }
}
