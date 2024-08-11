package com.curso.ecommerce.dao;

import org.springframework.data.repository.CrudRepository;

import com.curso.ecommerce.model.Empleado;


public interface EmpleadoDao extends CrudRepository<Empleado, Integer> {

}
