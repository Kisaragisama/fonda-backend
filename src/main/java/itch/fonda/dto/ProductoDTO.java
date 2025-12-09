package itch.fonda.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProductoDTO {

    private Integer id;
    private String nombreProducto;
    private String descripcion;
    private Double precio;
    private Integer tipoId;
    private String tipoNombre;
    private Integer cantidad;  
}
