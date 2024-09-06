package com.poker.demo.servicio;

import java.util.List;

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
		mano.setPuntaje(110);
		mano.setTipoRegla("escalera real");
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
		mano.setPuntaje(90 + valorUltimaCarta);
		return true;
	}
	
	public RespuestaGanador validarManoGanadora(Mano mano1, Mano mano2, String[] listaCartasMano1, String[] listaCartasMano2) {
		RespuestaGanador ganador = new RespuestaGanador();
		
	
		// Validamos mano 1
		if(esEscaleraReal(mano1)) {
			mano1.setTipoRegla("escalera real");
		}else if(esEscaleraDeColor(mano1)) {
			mano1.setTipoRegla("escalera de color");
		}
		
		// Validamos mano 2
		if(esEscaleraReal(mano2)) {
			mano2.setTipoRegla("escalera real");
		}else if(esEscaleraDeColor(mano2)) {
			mano2.setTipoRegla("escalera de color");
		}
		
		// Elegimos la mano ganadora
		if(mano1.getPuntaje() > mano2.getPuntaje()) {
			ganador.setCompositionWinnerHand(listaCartasMano1);
			ganador.setWinnerhand("mano 1");
			ganador.setWinnerHandType(mano1.getTipoRegla());
			return ganador;
		}
		
		ganador.setCompositionWinnerHand(listaCartasMano2);
		ganador.setWinnerhand("mano 2");
		ganador.setWinnerHandType(mano2.getTipoRegla());
		
		return ganador;
	}
	
	
	public int esEmpate(Mano mano1, Mano mano2) {
		return 0;
	}
	
	
}
