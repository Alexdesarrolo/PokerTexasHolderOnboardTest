package com.poker.demo.controlador;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.poker.demo.excepciones.Errores;
import com.poker.demo.modelo.Carta;
import com.poker.demo.modelo.HandsRequest;
import com.poker.demo.modelo.Mano;
import com.poker.demo.modelo.RespuestaGanador;
import com.poker.demo.servicio.ValidarManoReglas;

@RestController
public class PokerControlador {
	
	@PostMapping("/validar")
	public ResponseEntity<?> validar(@RequestBody HandsRequest manosSolicitud) {
		RespuestaGanador respuestaGanador = new RespuestaGanador();
		ValidarManoReglas validarManoReglas = new ValidarManoReglas();
		
		String[] listaCartas1 = manosSolicitud.getHand1().split(" ");
		String[] listaCartas2 = manosSolicitud.getHand2().split(" ");
		
		
			 
		
		if(listaCartas1.length != 5 || listaCartas2.length != 5) {
			
			return ResponseEntity.badRequest().body(new Errores("Ambas manos deben contener 5 cartas"));
			//return new ResponseEntity<>(respuestaGanador, HttpStatus.BAD_REQUEST);
		}
		
		Mano mano1 = new Mano();
		boolean validardoMano1 = mano1.pasarCadenaALista( listaCartas1 ); // Le pasamos las cartas en un arreglo
		Mano mano2 = new Mano();
		boolean validardoMano2 = mano2.pasarCadenaALista(listaCartas2);
		
		if(!validardoMano1 || !validardoMano2) {
			return ResponseEntity.badRequest().body(new Errores("Las cartas no estan bien definidas"));
		}
		
		
		// Validamos las reglas
		/*if(validarManoReglas.esEscaleraReal(mano1) != -1){
			System.out.println("Es escalera real");
		}
		
		if(validarManoReglas.esEscaleraDeColor(mano2) != -1) {
			System.out.println("Es escalera de color");
		}*/
		respuestaGanador = validarManoReglas.validarManoGanadora(mano1, mano2, listaCartas1, listaCartas1);
		
		
		return new ResponseEntity<>(respuestaGanador, HttpStatus.OK);
	}
}
