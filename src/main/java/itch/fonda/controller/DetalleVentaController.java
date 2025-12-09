package itch.fonda.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import itch.fonda.entity.DetalleVenta;
import itch.fonda.service.DetalleVentaService;

import java.util.List;

@RestController
@RequestMapping("/detalles-venta")
@CrossOrigin(origins = "*")
public class DetalleVentaController {

    @Autowired
    private DetalleVentaService detalleVentaService;

    @GetMapping
    public List<DetalleVenta> listar() {
        return detalleVentaService.listar();
    }

    @PostMapping
    public DetalleVenta crear(@RequestBody DetalleVenta detalle) {
        return detalleVentaService.guardar(detalle);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        detalleVentaService.eliminar(id);
    }
    
 // ✅ Nuevo endpoint de test
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Microservicio Productos activo y funcionando!");
    }
    
}