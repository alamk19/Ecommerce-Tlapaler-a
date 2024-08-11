package com.curso.ecommerce.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.ecommerce.dao.VentaDao;
import com.curso.ecommerce.model.Venta;


@Service
public class VentaServicelmpl implements VentaService {
	@Autowired
	private VentaDao ventaDao;
	
	public List<Venta> listarVenta() {
		return (List<Venta>) ventaDao.findAll();
	}

	public void guardar(Venta venta) {
		ventaDao.save(venta);

	}

	public void eliminar(Venta venta) {
		ventaDao.delete(venta);

	}
	public Venta encontrarVenta(Venta venta) {
		return ventaDao.findById(venta.getId()).orElse(null);
	}

}
