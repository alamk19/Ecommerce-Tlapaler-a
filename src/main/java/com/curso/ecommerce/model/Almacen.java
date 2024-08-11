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
@Table (name="almacen")
public class Almacen {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;
@Column
@Min(value=1,message="no debe estar vacío")
private int cantidad;
@NotEmpty
private String fechaIngreso;
private String fechaEgreso;
@Min(value=1,message="no debe estar vacío")
private int idProducto;
@Min(value=1,message="no debe estar vacío")
private int idEmpleado;
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
public String getFechaIngreso() {
	return fechaIngreso;
}
public void setFechaIngreso(String fechaIngreso) {
	this.fechaIngreso = fechaIngreso;
}
public String getFechaEgreso() {
	return fechaEgreso;
}
public void setFechaEgreso(String fechaEgreso) {
	this.fechaEgreso = fechaEgreso;
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
@Override
public String toString() {
	return "Almacen [id=" + id + ", cantidad=" + cantidad + ", fechaIngreso=" + fechaIngreso + ", fechaEgreso="
			+ fechaEgreso + ", idProducto=" + idProducto + ", idEmpleado=" + idEmpleado + "]";
}

}
