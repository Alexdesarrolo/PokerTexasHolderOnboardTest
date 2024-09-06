package com.poker.demo.modelo;

import java.util.ArrayList;
import java.util.List;

public class RespuestaGanador {
	
	private String winnerhand;
	private String winnerHandType;
	private List<Carta> compositionWinnerHand;
	
	public RespuestaGanador() {
		compositionWinnerHand = new ArrayList<>();
		compositionWinnerHand.add(new Carta("", ""));
	}
	
	public String getWinnerhand() {
		return winnerhand;
	}
	public void setWinnerhand(String winnerhand) {
		this.winnerhand = winnerhand;
	}
	public String getWinnerHandType() {
		return winnerHandType;
	}
	public void setWinnerHandType(String winnerHandType) {
		this.winnerHandType = winnerHandType;
	}
	public List<Carta> getCompositionWinnerHand() {
		return compositionWinnerHand;
	}
	public void setCompositionWinnerHand(List<Carta> compositionWinnerHand) {
		this.compositionWinnerHand = compositionWinnerHand;
	}
	
	
}
