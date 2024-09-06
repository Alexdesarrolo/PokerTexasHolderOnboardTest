package com.poker.demo.modelo;

import java.util.ArrayList;
import java.util.List;

public class Mano {
	
	private List<Carta> cartasMano;
	
	public Mano() {
		this.cartasMano = new ArrayList<>();
	}
	
	public void agregarCarta(Carta carta) {
		this.cartasMano.add(carta);
	}
	
	public List<Carta> getCartasMano(){
		return this.cartasMano;
	}
	
	public boolean pasarCadenaALista (String[] mano) {
		for(String cartaM: mano) {
			//Validamos si es de 3 caracteres la carta
			int longitud = cartaM.length();

			String valor = cartaM.substring(0, longitud-1);
			String palo = cartaM.substring(longitud-1, longitud); // último carácter
			
			
			Carta carta = new Carta(valor, palo);
			
			if(!carta.validarPalo() || !carta.validarValor()) {
				return false;
			}
			carta.setValor(valor); // Solo llamamos el método para convierta las letras en números, si las hay
			
			this.cartasMano.add(carta);
		}
		ordenarAsc(); // Ordenamos la lista por los valores ascendentemente
		return true;
	}
	
	public void ordenarAsc() {
		int longitud = this.cartasMano.size();
		for(int i = 0; i < longitud; i++) {
			for(int j = 0; j < longitud-1; j++) {
				if(Integer.parseInt(this.cartasMano.get(j).getValor()) > Integer.parseInt( this.cartasMano.get(j+1).getValor()) ) {
					Carta aux = this.cartasMano.get(j);
					this.cartasMano.set(j, this.cartasMano.get(j+1));
					this.cartasMano.set(j+1, aux);
				}
			}
		}
	}
	
	
}
