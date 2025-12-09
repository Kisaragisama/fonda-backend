package itch.fonda.service.Imp;

import itch.fonda.entity.DetalleVenta;
import itch.fonda.repository.DetalleVentaRepository;
import itch.fonda.service.DetalleVentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DetalleVentaServiceImpl implements DetalleVentaService {

    @Autowired
    private DetalleVentaRepository detalleVentaRepository;

    @Override
    public List<DetalleVenta> listar() {
        return detalleVentaRepository.findAll();
    }

    @Override
    public DetalleVenta guardar(DetalleVenta detalle) {
        return detalleVentaRepository.save(detalle);
    }

    @Override
    public void eliminar(Long id) {
        detalleVentaRepository.deleteById(id);
    }
}