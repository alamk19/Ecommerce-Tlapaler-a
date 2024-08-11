package com.curso.ecommerce.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.ecommerce.dao.AlmacenDao;
import com.curso.ecommerce.model.Almacen;

@Service
public class AlmacenServicelmpl implements AlmacenService {
	@Autowired
	private AlmacenDao almacenDao;
	
	
	public List<Almacen> listarAlmacen(){
		return (List<Almacen>) almacenDao.findAll();
	}

	public void guardar(Almacen almacen) {
		almacenDao.save(almacen);

	}

	public void eliminar(Almacen almacen) {
		almacenDao.delete(almacen);

	}
	public Almacen encontrarAlmacen(Almacen almacen) {
		return almacenDao.findById(almacen.getId()).orElse(null);
	}

}
