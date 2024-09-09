package com.poker.demo.modelo;

import java.util.ArrayList;
import java.util.List;

public class RespuestaGanador {
	

	private String winnerHand;
	private String winnerHandType;
	private List<String> compositionWinnerHand;
	//private String compositionWinnerHand[];
	
	public RespuestaGanador() {
		compositionWinnerHand = new ArrayList<>();
		//this.compositionWinnerHand = new String[5];
	}
	
	public String getWinnerHand() {
		return winnerHand;
	}
	public void setWinnerHand(String winnerhand) {
		this.winnerHand = winnerhand;
	}
	public String getWinnerHandType() {
		return winnerHandType;
	}
	public void setWinnerHandType(String winnerHandType) {
		this.winnerHandType = winnerHandType;
	}

	public List<String> getCompositionWinnerHand() {
		return compositionWinnerHand;
	}

	public void setCompositionWinnerHand(List<Carta> compositionWinnerHand) {
		for(Carta carta: compositionWinnerHand) {
			carta.convertirDeNumeroALetra(); // volvemos a pasar de números (11 - 14) a letras, si los hay
			this.compositionWinnerHand.add(carta.getValor());
		}
		//this.compositionWinnerHand = compositionWinnerHand;
	}
	
	/*public String[] getCompositionWinnerHand() {
		return compositionWinnerHand;
	}
	public void setCompositionWinnerHand(String[] compositionWinnerHand) {
		
		//Convertimos la lista de cartas del tipo Carta a String
		/*for(Carta carta: compositionWinnerHand) {
			carta.convertirDeNumeroALetra(); // volvemos a pasar de números (11 - 14) a letras, si los hay
			
			//this.compositionWinnerHand.add(winnerHandType)
		}
		
		this.compositionWinnerHand = compositionWinnerHand;
	}*/
	
	
}
