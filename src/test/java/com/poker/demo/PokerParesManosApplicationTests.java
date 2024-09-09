package com.poker.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.poker.demo.modelo.HandsRequest;
import com.poker.demo.modelo.Mano;
import com.poker.demo.modelo.RespuestaGanador;
import com.poker.demo.servicio.ValidarManoReglas;

@SpringBootTest
class PokerParesManosApplicationTests {

	 @Autowired
	 private ValidarManoReglas manoReglasService;

    @Test
    public void testGanadorEntreDosManos() {
        HandsRequest handsRequest = new HandsRequest("AH 10H JH QH KH", "2C 3H 4S 8C AH");
        
        String[] listaCartas1 = handsRequest.getHand1().split(" ");
		String[] listaCartas2 = handsRequest.getHand2().split(" ");
		
        Mano mano1 = new Mano();
		boolean validardoMano1 = mano1.pasarCadenaALista( listaCartas1 ); // Le pasamos las cartas en un arreglo
		Mano mano2 = new Mano();
		boolean validardoMano2 = mano2.pasarCadenaALista(listaCartas2);

        RespuestaGanador resultado = manoReglasService.validarManoGanadora(mano1, mano2, listaCartas1, listaCartas2);
        
        
        assertEquals("hand1", resultado.getWinnerHand());
        assertEquals("RoyalFlush", resultado.getWinnerHandType());
    }

}
