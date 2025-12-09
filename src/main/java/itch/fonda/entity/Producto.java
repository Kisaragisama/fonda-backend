package itch.fonda.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name ="producto")
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;
    
    @Column(name = "nombreProducto")
    private String nombreProducto;
    
    @Column(name = "descripcion")
    private String descripcion;
    
    @Column(name = "precio")
    private Double precio; 
    
    @ManyToOne
    @JoinColumn(name = "idTipo")
    private Tipo tipo;
    
    @Column(name = "activo")
    private boolean activo = true;
}