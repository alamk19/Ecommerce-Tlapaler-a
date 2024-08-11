package com.curso.ecommerce.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.ecommerce.dao.PedidoDao;
import com.curso.ecommerce.model.Pedido;


@Service
public class PedidoServicelmpl implements PedidoService {
	@Autowired
	private PedidoDao pedidoDao;
	
	public List<Pedido> listarPedido() {
		return (List<Pedido>) pedidoDao.findAll();
	}

	public void guardar(Pedido pedido) {
		pedidoDao.save(pedido);

	}

	public void eliminar(Pedido pedido) {
		pedidoDao.delete(pedido);

	}
	public Pedido encontrarPedido(Pedido pedido) {
		return pedidoDao.findById(pedido.getId()).orElse(null);
	}

}
