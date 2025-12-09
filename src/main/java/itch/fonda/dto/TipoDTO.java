package itch.fonda.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoDTO {

    private Integer id;
    private String tipo_producto;
    private String descripcion;
}