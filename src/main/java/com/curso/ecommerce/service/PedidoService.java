package com.curso.ecommerce.service;

import java.util.List;

import com.curso.ecommerce.model.Pedido;



public interface PedidoService {
	public List<Pedido> listarPedido();
	public void guardar(Pedido pedido);
	public void eliminar(Pedido pedido);
	public Pedido encontrarPedido(Pedido pedido);

}