package com.poker.demo.servicio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.poker.demo.modelo.Carta;
import com.poker.demo.modelo.Mano;
import com.poker.demo.modelo.RespuestaGanador;

@Service
public class ValidarManoReglas {
	
	public boolean esEscaleraReal(Mano mano) {
		List<String> valoresRealeas = List.of("10", "11", "12", "13", "14");
		
		if(esMismoPalo( mano)) {
			for(Carta carta: mano.getCartasMano()) {
				
				if(!valoresRealeas.contains(carta.getValor())) { // Validamos si cada valor esta en la lista de valoresReales 
					return false;
				}
			}
			mano.setPuntaje(200);
			//mano.setTipoRegla("escalera real");
			return true;
		}
		return false;
	}
	
	public boolean esEscaleraDeColor(Mano mano) {
		int contar = Integer.parseInt(mano.getCartasMano().get(0).getValor()); // obtenemos el primer valor de la lista
		if(esMismoPalo( mano) && esValoresConsecutivos(mano)) {
			/*for(Carta carta: mano.getCartasMano()) {
				
				if(!(Integer.parseInt(carta.getValor()) ==  contar++)) { // Validamos los valores 
					return false;
				}
			}*/
			int longitudLista = mano.getCartasMano().size()-1;
			int valorUltimaCarta = Integer.parseInt(mano.getCartasMano().get(longitudLista).getValor());
			mano.setPuntaje(180 + valorUltimaCarta);
			return true;
		}
		return false;
		
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
				mano.setPuntaje(160 + valorCarta);
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
			
			System.out.println(valoresIguales.get(valor));
			if(valoresIguales.get(valor) == 3) {
				valorCarta = Integer.parseInt(valor);
				continue;
			}else if (valoresIguales.get(valor) == 2) {
				continue;
			}
			return false;
			
		}
		mano.setPuntaje(140 + valorCarta);
		return true;
	}
	
	public boolean esMismoPalo(Mano mano) {
		String carta1 = mano.getCartasMano().get(0).getPalo();
		for(Carta carta: mano.getCartasMano()) {
			if(!carta1.equals(carta.getPalo())) { //Verificamos si es del mismo palo
				return false;
			}
		}
		return true;
	}
	
	public List<Integer> devolverValores(Mano mano){
		// Este método retornará una lista de valores.
		// Se utiliza cuando las manos tienen las mismas combinaciones, 
		// y la lista retornada se usa en otro método para desempatar, en caso de ser necesario.

		
		 // Convertir el array de String a una lista de Integer 
        List<Integer> listaValores = mano.getCartasMano().stream()
           .map(s -> Integer.parseInt(s.getValor())) // Convierte cada string en int
           .collect(Collectors.toList()); // Colecciona en una lista
        
        return listaValores;
	}
	
	public List<Integer> esColor(Mano mano){
		// Este método retornará una lista de valores.
		// Se utiliza cuando las manos tienen las mismas combinaciones, 
		// y la lista retornada se usa en otro método para desempatar, en caso de ser necesario.

        List<Integer> listaValores = devolverValores(mano);
        
        int valorCarta = listaValores.get(listaValores.size()-1);
        mano.setPuntaje(120 + valorCarta);
		
		return listaValores;
	}
	
	public boolean esValoresConsecutivos(Mano mano) {
		int contar = Integer.parseInt(mano.getCartasMano().get(0).getValor()); // obtenemos el primer valor de la lista
		for(Carta carta: mano.getCartasMano()) {
			
			if(!(Integer.parseInt(carta.getValor()) ==  contar++)) { // Validamos los valores que sea consecutivo
				return false;
			}
		}
		return true;
	}
	
	public List<Integer> esEscalera(Mano mano) {
		// Este método retornará una lista de valores.
		// Se utiliza cuando las manos tienen las mismas combinaciones, 
		// y la lista retornada se usa en otro método para desempatar, en caso de ser necesario.
		List<Integer> listaValores = devolverValores(mano);
		
		
		int valorCartaMayor = listaValores.get(listaValores.size()-1);
        mano.setPuntaje(100 + valorCartaMayor);
		
		return listaValores;		
	}
	
	public boolean esTrio(Mano mano) {
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
		
		for(Map.Entry<String, Integer> elemento: valoresIguales.entrySet()) { // validamos si en diccionario hay algún valor con 3
			String valor = elemento.getKey();
			
			System.out.println(valoresIguales.get(valor));
			if(valoresIguales.get(valor) == 3) {
				valorCarta = Integer.parseInt(valor);
				mano.setPuntaje(80 + valorCarta);
				return true;
			}
			
		}
		
		return false;
	}
	
	public String cartaMasAlta(List<Integer> listaMano1, List<Integer>  listaMano2, Mano mano1, Mano mano2) {
		for(int i  = 0; i < listaMano1.size(); i++) {
			int valorCartaMano1 = listaMano1.get(i);
			int valorCartaMano2 = listaMano2.get(i);
			System.out.println("--> " + listaMano1.get(i) + " --> " + listaMano2.get(i));
			if(valorCartaMano1 > valorCartaMano2) {
				mano1.setPuntaje(mano1.getPuntaje() + valorCartaMano1);
				return "mano1";
			}else if (valorCartaMano1 < valorCartaMano2) {
				mano2.setPuntaje(mano2.getPuntaje() + valorCartaMano1);
				return "mano2";
			}
		}
		return "empate"; // Si llega aquí la partida es empate 
	}
	
	public RespuestaGanador validarManoGanadora(Mano mano1, Mano mano2, String[] listaCartasMano1, String[] listaCartasMano2) {
		
		// Listas para el caso que se necesite desempatar en las reglas color, par y doble par
		List<Integer> valoresColorMano1 = null;
		List<Integer> valoresColorMano2= null;
		
		List<Integer> valoresEscaleraMano1 = null;
		List<Integer> valoresEscaleraMano2 = null;
		
		List<Integer> valoresParMano1= null;
		List<Integer> valoresParMano2= null;
		
		List<Integer> valoresDobleParMano1= null;
		List<Integer> valoresDobleParMano2= null;
		
		RespuestaGanador ganador = new RespuestaGanador();
		
		// Validamos mano 1
		if(esEscaleraReal(mano1)) {
			mano1.setTipoRegla("RoyalFlush");
		}else if(esEscaleraDeColor(mano1)) {
			mano1.setTipoRegla("StraightFlush");
			System.out.println("Escalera de color");
		}else if(esPoker(mano1)) {
			mano1.setTipoRegla("poker");
		}else if(esFullHouse(mano1)) {
			mano1.setTipoRegla("FullHouse");
		}else if(esMismoPalo(mano1)) { // Verifica la regla color
			valoresColorMano1 = esColor(mano1);
			System.out.println("Flush 1");
			mano1.setTipoRegla("Flush"); 
		}else if(esValoresConsecutivos(mano1)) { // Verifica la regla escalera
			valoresEscaleraMano1 = esEscalera(mano1);
			mano1.setTipoRegla("Straight"); 
		}else if (esTrio(mano1)) {
			mano1.setTipoRegla("ThreeOfAKind");
		}
		
		// Validamos mano 2
		if(esEscaleraReal(mano2)) {
			mano2.setTipoRegla("RoyalFlush");
		}else if(esEscaleraDeColor(mano2)) {
			mano2.setTipoRegla("StraightFlush");
		}else if(esPoker(mano2)) {
			mano2.setTipoRegla("poker");
		}else if(esFullHouse(mano2)) {
			mano2.setTipoRegla("Full House");
		}else if(esMismoPalo(mano2)) { // Valida la regla de color
			
			valoresColorMano2 = esColor(mano2);
			mano2.setTipoRegla("Flush"); 
			System.out.println("Flush 2");
			System.out.println("Tamaño " + valoresColorMano2.size());
		}else if(esValoresConsecutivos(mano2)) { // Verifica la regla escalera
			valoresEscaleraMano2 = esEscalera(mano2);
			mano2.setTipoRegla("Straight"); 
		}
		
		// Validamos si algún empate que se pueda desempatar
		if(mano1.getPuntaje() == mano2.getPuntaje()){
			System.out.println("Es Empate ----");
			if(mano1.getTipoRegla().equals("Flush")) {				
				cartaMasAlta(valoresEscaleraMano1, valoresColorMano2, mano1, mano2);
			}else if(mano1.getTipoRegla().equals("Straight")) {				
				cartaMasAlta(valoresEscaleraMano2, valoresEscaleraMano2, mano1, mano2);
			}
		}
		
		// Si los puntajes siguen igual la partida es empate
		if(mano1.getPuntaje() == mano2.getPuntaje()){
			System.out.println("Quedó Empate ----");
			ganador.setCompositionWinnerHand(new String[]{"empate"});
			ganador.setWinnerHand("empate");
			ganador.setWinnerHandType("empate");
			return ganador;
		}
		
		
		// Elegimos la mano ganadora
		if(mano1.getPuntaje() > mano2.getPuntaje()) {
			System.out.println("Mano1 " + mano1.getTipoRegla());
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
