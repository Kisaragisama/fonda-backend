package itch.fonda.mapper;

import itch.fonda.dto.VentaDTO;
import itch.fonda.dto.DetalleVentaDTO;
import itch.fonda.entity.Venta;
import itch.fonda.entity.DetalleVenta;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class VentaMapper {

    // Entidad -> DTO
    public static VentaDTO mapToDTO(Venta venta) {
        VentaDTO dto = new VentaDTO();
        dto.setId(venta.getId());
        dto.setClienteId(venta.getClienteId());
        dto.setFecha(venta.getFecha());
        dto.setTotal(venta.getTotal());

        List<DetalleVentaDTO> detallesDTO = Optional.ofNullable(venta.getDetalles())
                .orElse(Collections.emptyList())
                .stream()
                .map(VentaMapper::mapDetalleToDTO)
                .collect(Collectors.toList());
        dto.setDetalles(detallesDTO);

        return dto;
    }

    // DTO -> Entidad
    public static Venta mapToEntity(VentaDTO dto) {
        Venta venta = new Venta();
        venta.setId(dto.getId());
        venta.setClienteId(dto.getClienteId());
        venta.setFecha(dto.getFecha() != null ? dto.getFecha() : java.time.LocalDateTime.now());
        venta.setTotal(dto.getTotal());

        List<DetalleVenta> detalles = Optional.ofNullable(dto.getDetalles())
                .orElse(Collections.emptyList())
                .stream()
                .map(det -> mapDetalleToEntity(det, venta))
                .collect(Collectors.toList());
        venta.setDetalles(detalles);

        return venta;
    }

    // DetalleVenta Entidad -> DTO
    private static DetalleVentaDTO mapDetalleToDTO(DetalleVenta detalle) {
        DetalleVentaDTO dto = new DetalleVentaDTO();
        dto.setId(detalle.getId());
        dto.setProductoId(detalle.getProductoId());
        dto.setNombreProducto(detalle.getNombreProducto());
        dto.setCantidad(detalle.getCantidad());
        dto.setPrecioUnitario(detalle.getPrecioUnitario());
        dto.setSubtotal(detalle.getSubtotal());
        return dto;
    }

    // DetalleVenta DTO -> Entidad
    public static DetalleVenta mapDetalleToEntity(DetalleVentaDTO dto, Venta venta) {
        DetalleVenta detalle = new DetalleVenta();
        detalle.setId(dto.getId());
        detalle.setProductoId(dto.getProductoId());
        detalle.setNombreProducto(dto.getNombreProducto());
        detalle.setCantidad(dto.getCantidad());
        detalle.setPrecioUnitario(dto.getPrecioUnitario());
        detalle.setSubtotal(dto.getSubtotal());
        detalle.setVenta(venta);
        return detalle;
    }
}