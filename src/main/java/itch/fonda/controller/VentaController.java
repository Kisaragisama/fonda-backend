package itch.fonda.controller;

import itch.fonda.dto.ProductoDTO;
import itch.fonda.dto.VentaDTO;
import itch.fonda.service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ventas")
@CrossOrigin("*")
public class VentaController {

    @Autowired
    private VentaService ventaService;

    @PostMapping
    public VentaDTO createVenta(@RequestBody VentaDTO ventaDTO) {
        return ventaService.createVenta(ventaDTO);
    }

    @GetMapping
    public List<VentaDTO> getAllVentas() {
        return ventaService.getAllVentas();
    }

    @GetMapping("/{id}")
    public VentaDTO getVentaById(@PathVariable Long id) {
        return ventaService.getVentaById(id);
    }

    @PutMapping("/{id}")
    public VentaDTO updateVenta(@PathVariable Long id, @RequestBody VentaDTO ventaDTO) {
        return ventaService.updateVenta(id, ventaDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteVenta(@PathVariable Long id) {
        ventaService.deleteVenta(id);
    }
    
    @PutMapping("/{id}/productos")
    public VentaDTO asignarProductos(@PathVariable Long id, @RequestBody List<ProductoDTO> productos) {
        return ventaService.asignarProductos(id, productos);
    }

    // Nuevo endpoint de test
    @GetMapping("/test")
    public String testEndpoint() {
        return "Microservicio Fonda activo y funcionando!";
    }
}
