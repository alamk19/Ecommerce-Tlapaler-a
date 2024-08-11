package com.curso.ecommerce.service;

import java.util.List;

import com.curso.ecommerce.model.Inventario;




public interface InventarioService {
	public List<Inventario> listarInventario();
	public void guardar(Inventario inventario);
	public void eliminar(Inventario inventario);
	public Inventario encontrarInventario(Inventario inventario);

}