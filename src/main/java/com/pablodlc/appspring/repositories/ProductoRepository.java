package com.pablodlc.appspring.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pablodlc.appspring.models.ProductoModel;

// Decimos que es un Repositorio, y extiende de CrudReposity
// que contiene los metodos Crud sin que haga falta crearlo nosotros
// le pasamos el modelo y el tipo del identificador
@Repository
public interface ProductoRepository extends JpaRepository<ProductoModel, Long>{
    
}
