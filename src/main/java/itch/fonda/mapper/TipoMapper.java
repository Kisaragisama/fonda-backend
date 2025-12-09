package itch.fonda.mapper;

import itch.fonda.dto.TipoDTO;
import itch.fonda.entity.Tipo;

public class TipoMapper {

    public static TipoDTO mapToTipoDTO(Tipo tipo) {
        return new TipoDTO(
            tipo.getId(),
            tipo.getTipo_producto(),
            tipo.getDescripcion()
        );
    }

    public static Tipo mapToTipo(TipoDTO tipoDTO) {
        return new Tipo(
            tipoDTO.getId(),
            tipoDTO.getTipo_producto(),
            tipoDTO.getDescripcion()
        );
    }
}