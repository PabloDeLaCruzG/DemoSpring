package com.pablodlc.appspring.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pablodlc.appspring.dtos.ProductoDTO;
import com.pablodlc.appspring.models.ProductoModel;
import com.pablodlc.appspring.services.ProductoService;

// Indica que esta clase va a actuar como un controlador en la aplicación
// y define la ruta base para los endpoints de esta clase
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    // Inyecta la instancia de ProductoService para poder utilizar sus métodos
    @Autowired
    ProductoService productoService;

    // Método GET para obtener todos los productos
    @GetMapping()
    public ResponseEntity<?> getProductos() {
        return new ResponseEntity<>(productoService.getProductos(), HttpStatus.OK);
    }

    // Método POST para agregar un nuevo producto
    @PostMapping()
    public ResponseEntity<?> addProducto(@RequestBody ProductoDTO productoDto) {
        try {
            // Crea un nuevo producto a partir de los datos recibidos y lo agrega utilizando ProductoService
            productoService.addProducto(new ProductoModel(productoDto.name(), productoDto.price(), productoDto.img()));
            // Devuelve una respuesta indicando que el producto ha sido creado exitosamente
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            // Devuelve una respuesta de error si ocurre algún problema al agregar el producto
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para actualizar un producto por su ID
    @PutMapping("/{id}")
    public ResponseEntity<ProductoModel> actualizarProducto(@PathVariable Long id,
            @RequestBody ProductoModel productoActualizado) {
        try {
            // Actualiza el producto con el ID proporcionado utilizando los datos del producto actualizado
            ProductoModel producto = productoService.updateProducto(id, productoActualizado);
            // Devuelve una respuesta con el producto actualizado
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } catch (Exception e) {
            // Devuelve una respuesta de error si ocurre algún problema al actualizar el producto
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para eliminar un producto por su ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ProductoModel> deleteProducto(@PathVariable Long id) {
        try {
            // Elimina el producto con el ID proporcionado
            productoService.removeProducto(id);
            // Devuelve una respuesta indicando que el producto ha sido eliminado exitosamente
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            // Devuelve una respuesta de error si ocurre algún problema al eliminar el producto
            return ResponseEntity.notFound().build();
        }

    }
}


