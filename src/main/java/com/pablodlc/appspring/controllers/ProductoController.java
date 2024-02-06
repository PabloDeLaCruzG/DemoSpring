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

// Le decimos que su funcion va a ser la de controlador y
// le asignamos la ruta para decirle que direccion va a usar esta clase
@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    // Inyecta todos los metodos de la clase
    @Autowired
    ProductoService productoService;

    // Hacemos un metodo get para recibir los usuarios de la api
    @GetMapping()
    public ResponseEntity<?> getProductos() {
        return new ResponseEntity<>(productoService.getProductos(), HttpStatus.OK);
    }

    // Metodo para hacer un post a la api, RequestBody nos permite pasarle un
    // parametro por la url para hacer el post
    @PostMapping()
    public ResponseEntity<?> addProducto(@RequestBody ProductoDTO productoDto) {
        try {
            productoService.addProducto(new ProductoModel(productoDto.name(), productoDto.price(), productoDto.img()));
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para actualizar un producto por su ID
    @PutMapping("/{id}")
    public ResponseEntity<ProductoModel> actualizarProducto(@PathVariable Long id,
            @RequestBody ProductoModel productoActualizado) {
        try {
            ProductoModel producto = productoService.updateProducto(id, productoActualizado);
            return new ResponseEntity<>(producto, HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ProductoModel> deleteProducto(@PathVariable Long id) {
        try {
            productoService.removeProducto(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }

    }

}
