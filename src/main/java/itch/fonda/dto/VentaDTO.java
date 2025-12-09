package itch.fonda.dto;

import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaDTO {
    private Long id;
    private Integer clienteId;
    private LocalDateTime fecha;
    private Double total;
    private List<DetalleVentaDTO> detalles;
}