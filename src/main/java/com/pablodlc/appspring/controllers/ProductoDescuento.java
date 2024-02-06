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

    // Inyecta todos los metodos de la clase
    @Autowired
    ProductoService productoService;

    @GetMapping()
    public ResponseEntity<?> getProductosDescuentos() {

        List<ProductoModel> productos = productoService.getProductos();
        List<ProductoModel> productosRebajados = new ArrayList<ProductoModel>();

        for (ProductoModel productoModel : productos) {
            if (productoModel.getPrice() >= 50.00) {
                double nuevoPrecio = productoModel.getPrice() - (productoModel.getPrice() * 0.20);
                
                // Redondear a dos decimales
                BigDecimal bd = new BigDecimal(Double.toString(nuevoPrecio));
                bd = bd.setScale(2, RoundingMode.HALF_UP);

                nuevoPrecio = bd.doubleValue();
                productoModel.setPrice(nuevoPrecio);
                productosRebajados.add(productoModel);
            }
        }

        return new ResponseEntity<>(productosRebajados, HttpStatus.OK);
    }
}
