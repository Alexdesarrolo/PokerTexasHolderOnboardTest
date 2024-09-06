package com.poker.demo.modelo;

import java.util.List;

public class Carta {
	private String palo;
	private String valor;
	
	//public Carta() {}
	
	public Carta(String valor, String palo) {
		this.palo = palo;
		this.valor = valor;
	}
	
	public void setValor(String valor) {
		// Si los valores son letras los convertimos a número
		if(valor.equals("J")) {
			this.valor = "11";
		}else if(valor.equals("Q")) {
			this.valor = "12";
		}else if(valor.equals("K")) {
			this.valor = "13";
		}else if(valor.equals("A")) {
			this.valor = "14";
		}
		//this.valor = valor;
	}
	
	public String getValor() {
		return this.valor;
	}

	public String getPalo() {
		return palo;
	}

	public void setPalo(String palo) {
		this.palo = palo;
	}
	
	public boolean validarValor() {
		List<String> valoresValidos = List.of("2", "3", "4", "5", "6", "7", "8", "9","10", "J", "Q", "K", "A");
		
		return valoresValidos.contains(this.valor);
	}
	
	public boolean validarPalo() {
		List<String> palosValidos = List.of("C", "D", "H", "S");
		//System.out.println(this.palo);
		return palosValidos.contains(this.palo);
	}
	
	public void convertirDeNumeroALetra() {
		if(this.valor.equals("11")) {
			this.valor = "J";
		}else if(this.valor.equals("12")) {
			this.valor = "Q";
		}else if(this.valor.equals("13")) {
			this.valor = "K";
		}else if(this.valor.equals("14")) {
			this.valor = "A";
		}
	}
	
	

}
