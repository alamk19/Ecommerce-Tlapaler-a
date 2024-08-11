package com.curso.ecommerce.dao;

import org.springframework.data.repository.CrudRepository;

import com.curso.ecommerce.model.Proveedor;



public interface ProveedorDao extends CrudRepository<Proveedor, Integer> {

}
