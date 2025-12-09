package itch.fonda.service.Imp;

import itch.fonda.dto.ProductoDTO;
import itch.fonda.entity.Producto;
import itch.fonda.entity.Tipo;
import itch.fonda.mapper.ProductoMapper;
import itch.fonda.repository.ProductoRepository;
import itch.fonda.repository.TipoRepository;
import itch.fonda.service.ProductoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private TipoRepository tipoRepository;

    @Override
    public ProductoDTO createProducto(ProductoDTO productoDto) {
        Tipo tipo = tipoRepository.findById(productoDto.getTipoId())
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado con ID: " + productoDto.getTipoId()));

        Producto producto = ProductoMapper.mapToProducto(productoDto, tipo);
        producto.setActivo(true); // Siempre activo al crear
        Producto guardado = productoRepository.save(producto);

        return ProductoMapper.mapToProductoDTO(guardado);
    }

    @Override
    public ProductoDTO getProductoById(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        return ProductoMapper.mapToProductoDTO(producto);
    }

    @Override
    public List<ProductoDTO> getAllProductos() {
        List<Producto> productos = productoRepository.findByActivoTrue(); // Solo activos
        return productos.stream()
                .map(ProductoMapper::mapToProductoDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO updateProducto(Integer id, ProductoDTO productoDto) {
        Producto productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        productoExistente.setNombreProducto(productoDto.getNombreProducto());
        productoExistente.setDescripcion(productoDto.getDescripcion());
        productoExistente.setPrecio(productoDto.getPrecio());

        Tipo tipo = tipoRepository.findById(productoDto.getTipoId())
                .orElseThrow(() -> new RuntimeException("Tipo no encontrado con ID: " + productoDto.getTipoId()));
        productoExistente.setTipo(tipo);

        Producto actualizado = productoRepository.save(productoExistente);
        return ProductoMapper.mapToProductoDTO(actualizado);
    }

    @Override
    public void deleteProducto(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        producto.setActivo(false); // ← Soft delete
        productoRepository.save(producto);
    }

    @Override
    public void restoreProducto(Integer id) {
        Producto producto = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));
        producto.setActivo(true); // Restaurar producto
        productoRepository.save(producto);
    }
}