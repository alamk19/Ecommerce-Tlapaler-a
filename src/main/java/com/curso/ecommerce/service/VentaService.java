package com.curso.ecommerce.service;

import java.util.List;

import com.curso.ecommerce.model.Venta;




public interface VentaService {
	public List<Venta> listarVenta();
	public void guardar(Venta venta);
	public void eliminar(Venta venta);
	public Venta encontrarVenta(Venta venta);

}