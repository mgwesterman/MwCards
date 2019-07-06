package com.mw.games.cards.enums;

public enum Suit {

		CLUBS("C"),
		DIAMONDS("D"),
		HEARTS("H"),
		SPADES("S");
		
	private String identifier;
	
	Suit(String aIdentifier)
	{
		identifier = aIdentifier;
	}
	
	public String getIdentifier()
	{
		return identifier;
	}
}
