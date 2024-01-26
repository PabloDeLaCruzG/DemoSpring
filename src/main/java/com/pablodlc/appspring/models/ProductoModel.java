package com.pablodlc.appspring.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// Genera todos lo get
@Getter
// Genera todos los set
@Setter
// Genera el contructor vacio
@NoArgsConstructor
// Indica que es una entidad
@Entity
// Le pone ese nombre en vez del de la clase a la bd
@Table(name = "productos")
public class ProductoModel {
    
    // Asignamos que es id, que se genere auto y que sea unico y no pueda ser null
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;
    private String name;
    private Double price;

    public ProductoModel(String name, Double price) {
        this.name = name;
        this.price = price;
    }

}
