package com.curso.ecommerce.controller;

import java.util.Date;

import java.util.Random;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import com.curso.ecommerce.model.Mensaje;

@Controller
public class ChatControlador {
	
	private String[] colores = {"red","blue","green","orange","purple","brown"};
	@MessageMapping("/mensaje")
	@SendTo("/chat/mensaje")
	public Mensaje recibirMensaje(Mensaje mensaje) {
		mensaje.setFecha(new Date().getTime());
		if(mensaje.getTipo().equals("NUEVO_USUARIO")){
			mensaje.setColor(colores[new Random().nextInt(colores.length)]);
			mensaje.setTexto(" Nuevo usuario conectado");
		}
		return mensaje;
	}
	
	@MessageMapping("/escribiendo")
	@SendTo("/chat/escribiendo")
	public String comprobarSiElUsuarioEstaEscribiendo(String username) {
		return username.concat("está escribiendo...");
	}
}
