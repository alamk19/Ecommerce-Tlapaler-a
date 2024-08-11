package com.curso.ecommerce.dao;

import org.springframework.data.repository.CrudRepository;

import com.curso.ecommerce.model.Inventario;



public interface InventarioDao extends CrudRepository<Inventario, Integer> {

}
