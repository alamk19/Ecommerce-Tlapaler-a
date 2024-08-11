package com.curso.ecommerce.dao;

import org.springframework.data.repository.CrudRepository;

import com.curso.ecommerce.model.Producto;




public interface ProductoDao extends CrudRepository<Producto, Integer> {

}
