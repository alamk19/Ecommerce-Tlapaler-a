package com.curso.ecommerce.dao;

import org.springframework.data.repository.CrudRepository;

import com.curso.ecommerce.model.Cliente;


public interface ClienteDao extends CrudRepository<Cliente, Integer> {

}
