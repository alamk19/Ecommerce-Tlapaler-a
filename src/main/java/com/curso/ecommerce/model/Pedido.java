package com.curso.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

@Entity
@Table (name="pedido")
public class Pedido {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;
@Column
@Min(value=1,message="no debe estar vacío")
private int cantidad;
@Min(value=1,message="no debe estar vacío")
private double costo;
@NotEmpty
private String fecha;
private String fechaEntrega;
@Min(value=1,message="no debe estar vacío")
private int idProducto;
@Min(value=1,message="no debe estar vacío")
private int idEmpleado;
@Min(value=1,message="no debe estar vacío")
private int idProveedor;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public int getCantidad() {
	return cantidad;
}
public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}
public double getCosto() {
	return costo;
}
public void setCosto(double costo) {
	this.costo = costo;
}
public String getFecha() {
	return fecha;
}
public void setFecha(String fecha) {
	this.fecha = fecha;
}
public String getFechaEntrega() {
	return fechaEntrega;
}
public void setFechaEntrega(String fechaEntrega) {
	this.fechaEntrega = fechaEntrega;
}
public int getIdProducto() {
	return idProducto;
}
public void setIdProducto(int idProducto) {
	this.idProducto = idProducto;
}
public int getIdEmpleado() {
	return idEmpleado;
}
public void setIdEmpleado(int idEmpleado) {
	this.idEmpleado = idEmpleado;
}
public int getIdProveedor() {
	return idProveedor;
}
public void setIdProveedor(int idProveedor) {
	this.idProveedor = idProveedor;
}
@Override
public String toString() {
	return "Pedido [id=" + id + ", cantidad=" + cantidad + ", costo=" + costo + ", fecha=" + fecha + ", fechaEntrega="
			+ fechaEntrega + ", idProducto=" + idProducto + ", idEmpleado=" + idEmpleado + ", idProveedor="
			+ idProveedor + "]";
}

}
