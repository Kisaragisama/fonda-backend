package itch.fonda.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import itch.fonda.dto.ProductoDTO;
import itch.fonda.service.ProductoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    @PostMapping
    public ResponseEntity<ProductoDTO> createProducto(@RequestBody ProductoDTO productoDto) {
        ProductoDTO nuevoProducto = productoService.createProducto(productoDto);
        return ResponseEntity.ok(nuevoProducto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductoDTO> getProductoById(@PathVariable Integer id) {
        ProductoDTO producto = productoService.getProductoById(id);
        return ResponseEntity.ok(producto);
    }

    @GetMapping
    public ResponseEntity<List<ProductoDTO>> getAllProductos() {
        List<ProductoDTO> productos = productoService.getAllProductos();
        return ResponseEntity.ok(productos);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductoDTO> updateProducto(@PathVariable Integer id, @RequestBody ProductoDTO productoDto) {
        ProductoDTO productoActualizado = productoService.updateProducto(id, productoDto);
        return ResponseEntity.ok(productoActualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProducto(@PathVariable Integer id) {
        productoService.deleteProducto(id); // ← Soft delete
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/restore/{id}")
    public ResponseEntity<Void> restoreProducto(@PathVariable Integer id) {
        productoService.restoreProducto(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Nuevo endpoint de test
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Microservicio Productos activo y funcionando!");
    }
}
