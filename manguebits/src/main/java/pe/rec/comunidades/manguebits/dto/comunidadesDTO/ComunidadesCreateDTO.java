package pe.rec.comunidades.manguebits.dto.comunidadesDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import pe.rec.comunidades.manguebits.enums.Categoria;
import pe.rec.comunidades.manguebits.model.Comunidades;

import java.time.LocalDateTime;

public record ComunidadesCreateDTO(
        @NotBlank(message = "Atributo <nome> é obrigatório!") @NotEmpty String nome,
        @NotBlank(message = "Atributo <descricao> é obrigatório!") String descricao,
        @NotBlank(message = "Atributo <administrador> é obrigatório!") String administrador,
        @NotBlank(message = "Atributo <categoria> é obrigatório!") Categoria categoria
) {
    public static Comunidades toEntity(ComunidadesCreateDTO requestDTO) {
        return new Comunidades(
                null,
                requestDTO.nome(),
                requestDTO.descricao(),
                requestDTO.administrador(),
                requestDTO.categoria(),
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
