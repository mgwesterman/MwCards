package com.mw.games.cards;

public class TableConfig 
{
	private int tableMinBet = 5;
	private int tableMaxBet = 1000;
	private int blackJackPay = 150;
	
	public int getTableMinBet() {
		return tableMinBet;
	}
	public int getTableMaxBet() {
		return tableMaxBet;
	}
	public int getBlackJackPayPercentage() {
		return blackJackPay;
	} 

	
	
}
