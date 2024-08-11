package com.curso.ecommerce.dao;

import org.springframework.data.repository.CrudRepository;

import com.curso.ecommerce.model.Pedido;


public interface PedidoDao extends CrudRepository<Pedido, Integer> {

}
