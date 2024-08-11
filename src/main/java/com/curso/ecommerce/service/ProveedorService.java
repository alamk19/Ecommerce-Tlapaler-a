package com.curso.ecommerce.service;

import java.util.List;

import com.curso.ecommerce.model.Proveedor;




public interface ProveedorService {
	public List<Proveedor> listarProveedor();
	public void guardar(Proveedor proveedor);
	public void eliminar(Proveedor proveedor);
	public Proveedor encontrarProveedor(Proveedor proveedor);

}