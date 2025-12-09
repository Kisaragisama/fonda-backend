package itch.fonda.service;

import itch.fonda.entity.DetalleVenta;
import java.util.List;

public interface DetalleVentaService {
    List<DetalleVenta> listar();
    DetalleVenta guardar(DetalleVenta detalle);
    void eliminar(Long id);
}