package com.curso.ecommerce.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.ecommerce.dao.ProveedorDao;
import com.curso.ecommerce.model.Proveedor;


@Service
public class ProveedorServicelmpl implements ProveedorService {
	@Autowired
	private ProveedorDao proveedorDao;
	
	public List<Proveedor> listarProveedor() {
		return (List<Proveedor>) proveedorDao.findAll();
	}

	public void guardar(Proveedor proveedor) {
		proveedorDao.save(proveedor);

	}

	public void eliminar(Proveedor proveedor) {
		proveedorDao.delete(proveedor);

	}
	public Proveedor encontrarProveedor(Proveedor proveedor) {
		return proveedorDao.findById(proveedor.getId()).orElse(null);
	}

}
