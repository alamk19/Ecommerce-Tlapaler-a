package com.curso.ecommerce.service;

import java.util.List;

import com.curso.ecommerce.model.Empleado;


public interface EmpleadoService {
	public List<Empleado> listarEmpleado();
	public void guardar(Empleado empleado);
	public void eliminar(Empleado empleado);
	public Empleado encontrarEmpleado(Empleado empleado);

}