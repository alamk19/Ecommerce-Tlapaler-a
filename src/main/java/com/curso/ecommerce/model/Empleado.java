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
@Table (name="empleado")
public class Empleado {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;
@Column
@NotEmpty
private String nombre;
@NotEmpty
private String apellido;
@NotEmpty
private String direccion;
private String telefono;
private String fContratacion;
@Min(value=1,message="no debe estar vacío")
private double pago;
public Integer getId() {
	return id;
}
public void setId(Integer id) {
	this.id = id;
}
public String getNombre() {
	return nombre;
}
public void setNombre(String nombre) {
	this.nombre = nombre;
}
public String getApellido() {
	return apellido;
}
public void setApellido(String apellido) {
	this.apellido = apellido;
}
public String getDireccion() {
	return direccion;
}
public void setDireccion(String direccion) {
	this.direccion = direccion;
}
public String getTelefono() {
	return telefono;
}
public void setTelefono(String telefono) {
	this.telefono = telefono;
}
public String getfContratacion() {
	return fContratacion;
}
public void setfContratacion(String fContratacion) {
	this.fContratacion = fContratacion;
}
public double getPago() {
	return pago;
}
public void setPago(double pago) {
	this.pago = pago;
}
@Override
public String toString() {
	return "Empleado [id=" + id + ", nombre=" + nombre + ", apellido=" + apellido + ", direccion=" + direccion
			+ ", telefono=" + telefono + ", fContratacion=" + fContratacion + ", pago=" + pago + "]";
}


}
