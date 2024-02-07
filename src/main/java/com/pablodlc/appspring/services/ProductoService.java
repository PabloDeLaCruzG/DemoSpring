package com.pablodlc.appspring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

import com.pablodlc.appspring.models.ProductoModel;
import com.pablodlc.appspring.repositories.ProductoRepository;

// Hacemos la clase tipo service
@Service
public class ProductoService {

    // Asignmaos autowired para que inyecte y busque los metodos de jparepository
    // en la interfaz
    @Autowired
    ProductoRepository productoRepository;

    // Metodo que obtiene todos los productos
    public List<ProductoModel> getProductos() {
        return productoRepository.findAll();
    }

    // Metodo para guardar un producto, nonnull para que no pueda ser null
    public ProductoModel addProducto(@NonNull ProductoModel producto) {
        return productoRepository.save(producto);
    }

    // Método para actualizar un producto
    public ProductoModel updateProducto(Long id, ProductoModel productoActualizado) {
        
        @SuppressWarnings("null")
        // Verificar si el producto existe en la base de datos
        ProductoModel productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con ID: " + id));

        // Actualizar los campos del producto existente con los nuevos valores
        productoExistente.setName(productoActualizado.getName());
        productoExistente.setPrice(productoActualizado.getPrice());
        productoExistente.setImg(productoActualizado.getImg());

        // Guardar los cambios en la base de datos
        return productoRepository.save(productoExistente);
    }

    @SuppressWarnings("null")
    public void removeProducto(Long id) {
        productoRepository.deleteById(id);
    }
}