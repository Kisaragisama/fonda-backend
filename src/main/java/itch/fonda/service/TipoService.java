package itch.fonda.service;

import itch.fonda.dto.TipoDTO;
import java.util.List;

public interface TipoService {

    TipoDTO createTipo(TipoDTO tipoDto);

    TipoDTO getTipoById(Integer id);

    List<TipoDTO> getAllTipos();

    TipoDTO updateTipo(Integer id, TipoDTO tipoDto);

    void deleteTipo(Integer id);
}