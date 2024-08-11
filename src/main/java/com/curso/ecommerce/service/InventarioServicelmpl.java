package com.curso.ecommerce.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.ecommerce.dao.InventarioDao;
import com.curso.ecommerce.model.Inventario;


@Service
public class InventarioServicelmpl implements InventarioService {
	@Autowired
	private InventarioDao inventarioDao;
	
	public List<Inventario> listarInventario() {
		return (List<Inventario>) inventarioDao.findAll();
	}

	public void guardar(Inventario inventario) {
		inventarioDao.save(inventario);

	}

	public void eliminar(Inventario inventario) {
		inventarioDao.delete(inventario);

	}
	public Inventario encontrarInventario(Inventario inventario) {
		return inventarioDao.findById(inventario.getId()).orElse(null);
	}

}
