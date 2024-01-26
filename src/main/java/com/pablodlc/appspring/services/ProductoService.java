package com.pablodlc.appspring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.pablodlc.appspring.models.ProductoModel;
import com.pablodlc.appspring.repositories.ProductoRepository;

// Hacemos la clase tipo service
@Service
public class ProductoService {

    // Asignmaos autowired para que inyecte y busque los metodos de crudrepository en la interfaz
    @Autowired
    ProductoRepository productoRepository;

    // Metodo que obtiene todos los productos
    public List<ProductoModel> getProductos() { 
        return productoRepository.findAll(); 
    }

    // Metodo para guardar un producto, nonnull para que no pueda ser null
    public ProductoModel addProducto(@NonNull ProductoModel producto) {
        producto.setPrice(producto.getPrice() * 2);
        return productoRepository.save(producto);
    }
}