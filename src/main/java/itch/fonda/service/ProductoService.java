package itch.fonda.service;

import itch.fonda.dto.ProductoDTO;
import java.util.List;

public interface ProductoService {

    ProductoDTO createProducto(ProductoDTO productoDto);

    ProductoDTO getProductoById(Integer id);

    // Obtener solo productos activos
    List<ProductoDTO> getAllProductos();

    ProductoDTO updateProducto(Integer id, ProductoDTO productoDto);

    // Soft delete en vez de eliminar físicamente
    void deleteProducto(Integer id);

    // (Opcional) Restaurar un producto eliminado
    void restoreProducto(Integer id);
}