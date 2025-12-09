package itch.fonda.controller;

import itch.fonda.dto.TipoDTO;
import itch.fonda.service.TipoService;
import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@AllArgsConstructor
@RestController
@RequestMapping("/api/tipos")
public class TipoController {

    @Autowired
    private TipoService tipoService;

    @PostMapping
    public ResponseEntity<TipoDTO> createTipo(@RequestBody TipoDTO tipoDto) {
        return ResponseEntity.ok(tipoService.createTipo(tipoDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TipoDTO> getTipoById(@PathVariable Integer id) {
        return ResponseEntity.ok(tipoService.getTipoById(id));
    }

    @GetMapping
    public ResponseEntity<List<TipoDTO>> getAllTipos() {
        return ResponseEntity.ok(tipoService.getAllTipos());
    }

    @PutMapping("/{id}")
    public ResponseEntity<TipoDTO> updateTipo(@PathVariable Integer id, @RequestBody TipoDTO tipoDto) {
        return ResponseEntity.ok(tipoService.updateTipo(id, tipoDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTipo(@PathVariable Integer id) {
        tipoService.deleteTipo(id);
        return ResponseEntity.noContent().build();
    }

    //Nuevo endpoint de test
    @GetMapping("/test")
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("Microservicio Tipo activo y funcionando!");
    }
}
