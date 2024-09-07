package com.poker.demo.servicio;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.poker.demo.modelo.Carta;
import com.poker.demo.modelo.Mano;
import com.poker.demo.modelo.RespuestaGanador;

@Service
public class ValidarManoReglas {
	
	public boolean esEscaleraReal(Mano mano) {
		List<String> valoresRealeas = List.of("10", "11", "12", "13", "14");
		
		String carta1 = mano.getCartasMano().get(0).getPalo();
		for(Carta carta: mano.getCartasMano()) {
			if(!carta1.equals(carta.getPalo())) { //Verificamos si es del mismo palo
				return false;
			}
			
			if(!valoresRealeas.contains(carta.getValor())) { // Validamos si cada valor esta en la lista de valoresReales 
				return false;
			}
		}
		mano.setPuntaje(120);
		//mano.setTipoRegla("escalera real");
		return true;
	}
	
	public boolean esEscaleraDeColor(Mano mano) {
		String carta1 = mano.getCartasMano().get(0).getPalo(); // obtnemos el el primer palo de la lista 
		int contar = Integer.parseInt(mano.getCartasMano().get(0).getValor()); // obtenemos el primer valor de la lista
		for(Carta carta: mano.getCartasMano()) {
			if(!carta1.equals(carta.getPalo())) { //Verificamos si es del mismo palo
				return false;
			}
			
			
			if(!(Integer.parseInt(carta.getValor()) ==  contar++)) { // Validamos los valores 
				return false;
			}
		}
		int longitudLista = mano.getCartasMano().size()-1;
		int valorUltimaCarta = Integer.parseInt(mano.getCartasMano().get(longitudLista).getValor());
		mano.setPuntaje(100 + valorUltimaCarta);
		return true;
	}
	
	public boolean esPoker(Mano mano) {
		Map<String, Integer> valoresIguales = new HashMap<>();
		int valorCarta = 0;
		for(Carta carta: mano.getCartasMano()) {
			
			if(valoresIguales.containsKey(carta.getValor())){ // Si ya existe en el diccionario le sumamos +1
				valorCarta = Integer.parseInt(carta.getValor());
				valoresIguales.put(carta.getValor(), valoresIguales.get(carta.getValor()) + 1);
			}else {
				valoresIguales.put(carta.getValor(), 1);
			}
		}
		
		for(int value: valoresIguales.values()) { // Si contiene 4 como valor esPoker
			
			if(value == 4) {
				mano.setPuntaje(90 + valorCarta);
				return true;
			}
		}
		
		return false;
	}
	
	public boolean esFullHouse(Mano mano) {
		
		Map<String, Integer> valoresIguales = new HashMap<>();
		int valorCarta = 0;
		for(Carta carta: mano.getCartasMano()) {
			if(valoresIguales.containsKey(carta.getValor())){ // Si ya existe en el diccionario le sumamos +1
				
				valorCarta = Integer.parseInt(carta.getValor());
				valoresIguales.put(carta.getValor(), valoresIguales.get(carta.getValor()) + 1);
			}else {
				valoresIguales.put(carta.getValor(), 1);
			}
		}
		
		for(Map.Entry<String, Integer> elemento: valoresIguales.entrySet()) {
			String valor = elemento.getKey();
			int cantidadValor = elemento.getValue();
			
			System.out.println(valoresIguales.get(valor));
			if(valoresIguales.get(valor) == 3) {
				valorCarta = Integer.parseInt(valor);
				continue;
			}else if (valoresIguales.get(valor) == 2) {
				continue;
			}
			return false;
			
		}
		mano.setPuntaje(75 + valorCarta);
		return true;
	}
	
	
	public RespuestaGanador validarManoGanadora(Mano mano1, Mano mano2, String[] listaCartasMano1, String[] listaCartasMano2) {
		RespuestaGanador ganador = new RespuestaGanador();
		
		// Validamos mano 1
		if(esEscaleraReal(mano1)) {
			mano1.setTipoRegla("RoyalFlush");
		}else if(esEscaleraDeColor(mano1)) {
			mano1.setTipoRegla("escalera de color");
		}else if(esPoker(mano1)) {
			mano1.setTipoRegla("poker");
		}else if(esFullHouse(mano1)) {
			mano1.setTipoRegla("fullHouse");
		}
		
		// Validamos mano 2
		if(esEscaleraReal(mano2)) {
			mano2.setTipoRegla("RoyalFlush");
		}else if(esEscaleraDeColor(mano2)) {
			mano2.setTipoRegla("escalera de color");
		}else if(esPoker(mano2)) {
			mano2.setTipoRegla("poker");
		}else if(esFullHouse(mano2)) {
			mano2.setTipoRegla("Full House");
		}
		
		// Elegimos la mano ganadora
		if(mano1.getPuntaje() > mano2.getPuntaje()) {
			ganador.setCompositionWinnerHand(listaCartasMano1);
			ganador.setWinnerHand("hand1");
			ganador.setWinnerHandType(mano1.getTipoRegla());
			return ganador;
		}
		
		ganador.setCompositionWinnerHand(listaCartasMano2);
		ganador.setWinnerHand("hand2");
		ganador.setWinnerHandType(mano2.getTipoRegla());
		
		return ganador;
	}
	
	public int esEmpate(Mano mano1, Mano mano2) {
		return 0;
	}
	
	
}
