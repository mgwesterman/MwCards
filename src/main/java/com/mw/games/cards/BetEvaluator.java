package com.mw.games.cards;

public class BetEvaluator {

	private static final int DEFAULT_BET = 5;
	
	public int getNextBet(int lastBet)
	{
		if (lastBet > 0)
			return lastBet;
		else
			return DEFAULT_BET;
	}
}
