package com.pablodlc.appspring.controllers;

import java.util.*;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pablodlc.appspring.models.ProductoModel;
import com.pablodlc.appspring.services.ProductoService;

@RestController
@RequestMapping("/api/productos/ofertas")
public class ProductoDescuento {

    // Inyecta la instancia de ProductoService para poder utilizar sus métodos
    @Autowired
    ProductoService productoService;
    // Método GET para obtener productos con descuento
    @GetMapping()
    public ResponseEntity<?> getProductosDescuentos() {

        // Obtiene todos los productos de la base de datos
        List<ProductoModel> productos = productoService.getProductos();
        // Crea una lista para almacenar los productos con descuento
        List<ProductoModel> productosRebajados = new ArrayList<ProductoModel>();

        // Itera sobre cada producto para aplicar descuentos
        for (ProductoModel productoModel : productos) {
            // Verifica si el precio del producto es mayor o igual a 50.00
            if (productoModel.getPrice() >= 50.00) {
                // Calcula el nuevo precio con un descuento del 20%
                double nuevoPrecio = productoModel.getPrice() - (productoModel.getPrice() * 0.20);
                // Redondea el nuevo precio a dos decimales
                BigDecimal bd = new BigDecimal(Double.toString(nuevoPrecio));
                bd = bd.setScale(2, RoundingMode.HALF_UP);
                nuevoPrecio = bd.doubleValue();
                // Actualiza el precio del producto
                productoModel.setPrice(nuevoPrecio);
                // Agrega el producto con descuento a la lista
                productosRebajados.add(productoModel);
            }
        }
        // Devuelve una respuesta con la lista de productos con descuento
        return new ResponseEntity<>(productosRebajados, HttpStatus.OK);
    }
}
