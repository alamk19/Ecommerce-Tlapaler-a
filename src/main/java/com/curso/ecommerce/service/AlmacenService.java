package com.curso.ecommerce.service;

import java.util.List;

import com.curso.ecommerce.model.Almacen;


public interface AlmacenService {
	public List<Almacen> listarAlmacen();
	public void guardar(Almacen almacen);
	public void eliminar(Almacen almacen);
	public Almacen encontrarAlmacen(Almacen almacen);

}