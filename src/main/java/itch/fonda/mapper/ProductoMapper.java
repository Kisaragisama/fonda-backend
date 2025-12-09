package itch.fonda.mapper;

import itch.fonda.dto.ProductoDTO;
import itch.fonda.entity.Producto;
import itch.fonda.entity.Tipo;

public class ProductoMapper {

    // De DTO a entidad (requiere el tipo cargado desde el repositorio)
    public static Producto mapToProducto(ProductoDTO dto, Tipo tipo) {
        Producto producto = new Producto();
        producto.setId(dto.getId());
        producto.setNombreProducto(dto.getNombreProducto());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio()); // ahora Double
        producto.setTipo(tipo);
        return producto;
    }

    // De entidad a DTO
    public static ProductoDTO mapToProductoDTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombreProducto(producto.getNombreProducto());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio()); // ahora Double

        if (producto.getTipo() != null) {
            dto.setTipoId(producto.getTipo().getId());
            dto.setTipoNombre(producto.getTipo().getTipo_producto());
        }

        return dto;
    }
}
