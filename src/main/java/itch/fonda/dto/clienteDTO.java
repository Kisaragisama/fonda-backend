package itch.fonda.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class clienteDTO {

    private Integer id;            // Identificador del cliente
    private String nombreCliente;  // Nombre del cliente
    private int telefono;          // Teléfono
    private String correo;         // Correo electrónico
}