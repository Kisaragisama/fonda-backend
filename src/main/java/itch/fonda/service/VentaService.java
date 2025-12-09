package itch.fonda.service;

import itch.fonda.dto.ProductoDTO;
import itch.fonda.dto.VentaDTO;
import java.util.List;

public interface VentaService {
    VentaDTO createVenta(VentaDTO ventaDTO);
    List<VentaDTO> getAllVentas();
    VentaDTO getVentaById(Long id);
    VentaDTO updateVenta(Long id, VentaDTO ventaDTO);
    void deleteVenta(Long id);
    VentaDTO asignarProductos(Long ventaId, List<ProductoDTO> productos);
}