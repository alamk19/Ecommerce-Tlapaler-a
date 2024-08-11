package com.curso.ecommerce.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.ecommerce.dao.ClienteDao;
import com.curso.ecommerce.model.Cliente;


@Service
public class ClienteServicelmpl implements ClienteService {
	@Autowired
	private ClienteDao clienteDao;
	
	public List<Cliente> listarCliente() {
		return (List<Cliente>) clienteDao.findAll();
	}

	public void guardar(Cliente cliente) {
		clienteDao.save(cliente);

	}

	public void eliminar(Cliente cliente) {
		clienteDao.delete(cliente);

	}
	public Cliente encontrarCliente(Cliente cliente) {
		return clienteDao.findById(cliente.getId()).orElse(null);
	}

}
