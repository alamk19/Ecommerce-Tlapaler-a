package com.curso.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Min;

@Entity
@Table (name="inventario")
public class Inventario {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;
@Column
@Min(value=1,message="no debe estar vacío")
private int existencias;
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
public int getExistencias() {
	return existencias;
}
public void setExistencias(int existencias) {
	this.existencias = existencias;
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
	return "Inventario [id=" + id + ", existencias=" + existencias + ", idProducto=" + idProducto + ", idEmpleado="
			+ idEmpleado + "]";
}

}
