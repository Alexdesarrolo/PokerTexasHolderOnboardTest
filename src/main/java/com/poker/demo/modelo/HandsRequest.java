package com.poker.demo.modelo;

public class HandsRequest {
	
	private String hand1;
	private String hand2;
	
	public HandsRequest(String hand1, String hand2) {
		this.hand1 = hand1;
		this.hand2 = hand2;
	}
	
	public String getHand1() {
		return hand1;
	}
	public void setHand1(String hand1) {
		this.hand1 = hand1;
	}
	public String getHand2() {
		return hand2;
	}
	public void setHand2(String hand2) {
		this.hand2 = hand2;
	}
	
	
}
