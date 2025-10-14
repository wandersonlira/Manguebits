package pe.rec.comunidades.manguebits.dto.comunidadesDTO;

import jakarta.validation.constraints.NotBlank;
import pe.rec.comunidades.manguebits.enums.Categoria;

public record ComunidadesUpdateDTO(
        @NotBlank(message = "Atributo <nome> é obrigatório!") String nome,
        @NotBlank(message = "Atributo <descricao> é obrigatório!") String descricao,
        @NotBlank(message = "Atributo <categoria> é obrigatório!") Categoria categoria
) {}
