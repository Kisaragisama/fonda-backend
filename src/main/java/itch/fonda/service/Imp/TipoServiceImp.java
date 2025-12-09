package itch.fonda.service.Imp;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import itch.fonda.dto.TipoDTO;
import itch.fonda.entity.Tipo;
import itch.fonda.mapper.TipoMapper;
import itch.fonda.repository.TipoRepository;
import itch.fonda.service.TipoService;

@Service
public class TipoServiceImp implements TipoService {

    @Autowired
    private TipoRepository tipoRepository;

    @Override
    public TipoDTO createTipo(TipoDTO tipoDto) {
        Tipo tipo = TipoMapper.mapToTipo(tipoDto);
        Tipo guardado = tipoRepository.save(tipo);
        return TipoMapper.mapToTipoDTO(guardado);
    }

    @Override
    public TipoDTO getTipoById(Integer id) {
        Tipo tipo = tipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado con ID: " + id));
        return TipoMapper.mapToTipoDTO(tipo);
    }

    @Override
    public List<TipoDTO> getAllTipos() {
        return tipoRepository.findAll().stream().map(TipoMapper::mapToTipoDTO).collect(Collectors.toList());
    }

    @Override
    public TipoDTO updateTipo(Integer id, TipoDTO tipoDto) {
        Tipo tipo = tipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado con ID: " + id));
        tipo.setTipo_producto(tipoDto.getTipo_producto());
        Tipo actualizado = tipoRepository.save(tipo);
        return TipoMapper.mapToTipoDTO(actualizado);
    }

    @Override
    public void deleteTipo(Integer id) {
        Tipo tipo = tipoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado con ID: " + id));
        tipoRepository.delete(tipo);
    }
}
