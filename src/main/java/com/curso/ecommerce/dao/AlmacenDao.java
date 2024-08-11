package com.curso.ecommerce.dao;

import org.springframework.data.repository.CrudRepository;

import com.curso.ecommerce.model.Almacen;


public interface AlmacenDao extends CrudRepository<Almacen, Integer> {

}
