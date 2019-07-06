package com.mw.games.cards;

import com.mw.games.cards.enums.Rank;
import com.mw.games.cards.enums.Suit;

public class Deck {
	
	private Card[] cards = null;
	private int placeInTheDeck = 0;

	public Deck() {
		super();
		
		cards = new Card[52];
		int count=0;
		
		for (Suit suit : Suit.values()) {
		    for(Rank rank : Rank.values()) 
		    {
		    	if (!rank.equals(Rank.ACE_LOW))
		    	{
					Card c = new Card(suit,rank);
					//System.out.println(c);
					cards[count] = c;
					count++;
		    	}
		    }
		}
	}
	
	public int cardCount()
	{
		return cards.length;
	}
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<cards.length; i++)
		{
			sb.append(cards[i] + "\n");
		}
		return sb.toString();
	}
	
	public Card getNext()
	{
		Card c = cards[placeInTheDeck];
		placeInTheDeck++;
		return c;				
	}
}
