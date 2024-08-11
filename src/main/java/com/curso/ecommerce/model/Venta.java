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
@Table (name="venta")
public class Venta {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;
@Column
@NotEmpty
private String fecha;
@Min(value=1,message="no debe estar vacío")
private int cantidad;
@Min(value=1,message="no debe estar vacío")
private double precio;
@Min(value=1,message="no debe estar vacío")
private int idProducto;
@Min(value=1,message="no debe estar vacío")
private int idCliente;
@Min(value=1,message="no debe estar vacío")
private int idEmpleado;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getFecha() {
	return fecha;
}
public void setFecha(String fecha) {
	this.fecha = fecha;
}
public int getCantidad() {
	return cantidad;
}
public void setCantidad(int cantidad) {
	this.cantidad = cantidad;
}
public double getPrecio() {
	return precio;
}
public void setPrecio(double precio) {
	this.precio = precio;
}
public int getIdProducto() {
	return idProducto;
}
public void setIdProducto(int idProducto) {
	this.idProducto = idProducto;
}
public int getIdCliente() {
	return idCliente;
}
public void setIdCliente(int idCliente) {
	this.idCliente = idCliente;
}
public int getIdEmpleado() {
	return idEmpleado;
}
public void setIdEmpleado(int idEmpleado) {
	this.idEmpleado = idEmpleado;
}
@Override
public String toString() {
	return "Venta [id=" + id + ", fecha=" + fecha + ", cantidad=" + cantidad + ", precio=" + precio + ", idProducto="
			+ idProducto + ", idCliente=" + idCliente + ", idEmpleado=" + idEmpleado + "]";
}

}
