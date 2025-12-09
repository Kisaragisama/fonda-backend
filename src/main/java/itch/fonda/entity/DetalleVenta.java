package itch.fonda.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "detalle_venta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DetalleVenta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer productoId;

    private String nombreProducto;

    private Double precioUnitario;

    private Integer cantidad;

    private Double subtotal;

    @ManyToOne
    @JoinColumn(name = "venta_id")
    private Venta venta;
}