package com.curso.ecommerce.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.curso.ecommerce.dao.EmpleadoDao;
import com.curso.ecommerce.model.Empleado;

@Service
public class EmpleadoServicelmpl implements EmpleadoService {
	@Autowired
	private EmpleadoDao empleadoDao;
	
	public List<Empleado> listarEmpleado() {
		return (List<Empleado>) empleadoDao.findAll();
	}

	public void guardar(Empleado empleado) {
		empleadoDao.save(empleado);

	}

	public void eliminar(Empleado empleado) {
		empleadoDao.delete(empleado);

	}
	public Empleado encontrarEmpleado(Empleado empleado) {
		return empleadoDao.findById(empleado.getId()).orElse(null);
	}

}
