package itch.fonda.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVentaDTO {
    private Long id;
    private Integer productoId;
    private String nombreProducto;
    private Integer cantidad;
    private Double precioUnitario;
    private Double subtotal;
}