package itch.fonda.service.Imp;

import itch.fonda.dto.ProductoDTO;
import itch.fonda.dto.VentaDTO;
import itch.fonda.entity.Venta;
import itch.fonda.entity.DetalleVenta;
import itch.fonda.mapper.VentaMapper;
import itch.fonda.repository.VentaRepository;
import itch.fonda.service.VentaService;
import jakarta.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VentaServiceImpl implements VentaService {

    private final VentaRepository ventaRepository;

    @Autowired
    public VentaServiceImpl(VentaRepository ventaRepository) {
        this.ventaRepository = ventaRepository;
    }

    @Override
    public VentaDTO createVenta(VentaDTO ventaDTO) {
        Venta venta = VentaMapper.mapToEntity(ventaDTO);

        // Calcular total solo si hay detalles
        double total = venta.getDetalles() != null 
                       ? venta.getDetalles().stream().mapToDouble(DetalleVenta::getSubtotal).sum()
                       : 0.0;
        venta.setTotal(total);

        Venta saved = ventaRepository.save(venta);
        return VentaMapper.mapToDTO(saved);
    }

    @Override
    public List<VentaDTO> getAllVentas() {
        return ventaRepository.findAll()
                .stream()
                .map(VentaMapper::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public VentaDTO getVentaById(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        return VentaMapper.mapToDTO(venta);
    }

    @Override
    public VentaDTO updateVenta(Long id, VentaDTO ventaDTO) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        venta.setClienteId(ventaDTO.getClienteId());
        venta.setFecha(ventaDTO.getFecha());
        venta.setTotal(ventaDTO.getTotal());
        // actualizar detalles si quieres

        Venta updated = ventaRepository.save(venta);
        return VentaMapper.mapToDTO(updated);
    }

    @Override
    public void deleteVenta(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));
        ventaRepository.delete(venta);
    }

    @Override
    @Transactional
    public VentaDTO asignarProductos(Long ventaId, List<ProductoDTO> productos) {
        // Obtener la venta existente
        Venta venta = ventaRepository.findById(ventaId)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada"));

        // Inicializar lista de detalles si es null
        java.util.List<DetalleVenta> detallesExistentes = venta.getDetalles();
        if (detallesExistentes == null) {
            detallesExistentes = new java.util.LinkedList<>();
        }

        // Agregar productos nuevos
        for (ProductoDTO p : productos) {
            if (p.getId() == null) continue; // ignorar productos sin id válido

            boolean encontrado = false;

            // Verificar si ya existe el producto en la venta
            for (DetalleVenta d : detallesExistentes) {
                if (d.getProductoId() != null && d.getProductoId().equals(p.getId())) {
                    d.setCantidad(d.getCantidad() + (p.getCantidad() != null ? p.getCantidad() : 0));
                    d.setSubtotal(d.getCantidad() * (d.getPrecioUnitario() != null ? d.getPrecioUnitario() : 0));
                    encontrado = true;
                    break;
                }
            }

            // Si no se encontró, agregar como nuevo detalle
            if (!encontrado) {
                DetalleVenta d = new DetalleVenta();
                d.setProductoId(p.getId());
                d.setNombreProducto(p.getNombreProducto() != null ? p.getNombreProducto() : "Producto");
                double precio = p.getPrecio() != null ? p.getPrecio() : 0;
                d.setPrecioUnitario(precio);
                int cantidad = p.getCantidad() != null ? p.getCantidad() : 0;
                d.setCantidad(cantidad);
                d.setSubtotal(precio * cantidad);
                d.setVenta(venta); // vincular detalle con la venta
                detallesExistentes.add(d);
            }
        }

        venta.setDetalles(detallesExistentes);

        // Recalcular total
        double total = 0;
        for (DetalleVenta d : detallesExistentes) {
            total += d.getSubtotal() != null ? d.getSubtotal() : 0;
        }
        venta.setTotal(total);

        // Guardar y devolver
        Venta saved = ventaRepository.save(venta);
        return VentaMapper.mapToDTO(saved);
    }


}
