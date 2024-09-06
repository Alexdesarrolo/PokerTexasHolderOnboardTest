package com.poker.demo.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poker.demo.modelo.Carta;
import com.poker.demo.modelo.Mano;

@Service
public class ValidarManoReglas {
	
	public int esEscaleraReal(Mano mano) {
		List<String> valoresRealeas = List.of("10", "11", "12", "13", "14");
		
		String carta1 = mano.getCartasMano().get(0).getPalo();
		for(Carta carta: mano.getCartasMano()) {
			if(!carta1.equals(carta.getPalo())) { //Verificamos si es del mismo palo
				return -1;
			}
			
			if(!valoresRealeas.contains(carta.getValor())) { // Validamos los valores
				return -1;
			}
		}
		return 100;
	}
	
	public int esEscaleraDeColor(Mano mano) {
		String carta1 = mano.getCartasMano().get(0).getPalo(); // obtnemos el el primer palo de la lista 
		int contar = Integer.parseInt(mano.getCartasMano().get(0).getValor()); // obtenemos el primer valor de la lista
		for(Carta carta: mano.getCartasMano()) {
			if(!carta1.equals(carta.getPalo())) { //Verificamos si es del mismo palo
				return -1;
			}
			
			
			if(!(Integer.parseInt(carta.getValor()) ==  contar++)) { // Validamos los valores 
				return -1;
			}
		}
		
		return 90;
	}
	
	
}
