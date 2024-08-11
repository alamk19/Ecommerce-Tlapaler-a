package com.curso.ecommerce.service;

import java.util.List;

import com.curso.ecommerce.model.Cliente;





public interface ClienteService {
	public List<Cliente> listarCliente();
	public void guardar(Cliente cliente);
	public void eliminar(Cliente cliente);
	public Cliente encontrarCliente(Cliente cliente);

}