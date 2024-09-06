package com.poker.demo.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.poker.demo.modelo.Carta;
import com.poker.demo.modelo.Mano;

@Service
public class ValidarManoReglas {
	
	public int escaleraReal(Mano mano) {
		List<String> valoresRealeas = List.of("10", "11", "12", "13", "14");
		
		
		String carta1 = mano.getCartasMano().get(0).getPalo();
		for(Carta carta: mano.getCartasMano()) {
			if(!carta1.equals(carta.getPalo())) { //Verificamos si es del mismo palo
				System.out.println("No es el mismo palo");
				return -1;
			}
			
			if(!valoresRealeas.contains(carta.getValor())) { // Validamos los valores
				System.out.println("No es consecutivo");
				return -1;
			}
		}
		return 100;
	}
}
