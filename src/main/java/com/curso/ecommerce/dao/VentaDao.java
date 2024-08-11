package com.curso.ecommerce.dao;

import org.springframework.data.repository.CrudRepository;

import com.curso.ecommerce.model.Venta;



public interface VentaDao extends CrudRepository<Venta, Integer> {

}
