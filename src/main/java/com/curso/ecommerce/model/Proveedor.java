package com.curso.ecommerce.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table (name="proveedor")
public class Proveedor {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
private Integer id;
@Column
@NotEmpty
private String nombre;
@NotEmpty
private String telefono;
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
public String getTelefono() {
	return telefono;
}
public void setTelefono(String telefono) {
	this.telefono = telefono;
}
@Override
public String toString() {
	return "Proveedor [id=" + id + ", nombre=" + nombre + ", telefono=" + telefono + "]";
}

}
