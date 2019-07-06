package com.mw.games.cards;

import java.util.HashMap;
import java.util.Map;

import com.mw.games.cards.enums.Rank;
import com.mw.games.cards.enums.Suit;

public class Card implements Comparable<Card> {
	
	private Rank rank;
	private Suit suit;
	
	public static Map<Suit, Integer> defaultSuitOrder;
	static {
	    defaultSuitOrder = new HashMap<Suit, Integer>();
	    defaultSuitOrder.put(Suit.SPADES,4);
	    defaultSuitOrder.put(Suit.HEARTS,3);
	    defaultSuitOrder.put(Suit.DIAMONDS,2);
	    defaultSuitOrder.put(Suit.CLUBS,1);
	}

	public Card(Suit suit, Rank rank) {
		super();
		this.suit = suit;
		this.rank = rank;
	}

	public int compareTo(Card c2)
	{
		if (isMatch(c2))
			return 0;
		
		// if the ranks match, return based on order of suits
		if (isPair(c2))
			return defaultSuitOrder.get(getSuit()) - defaultSuitOrder.get(c2.getSuit());
		else
			return getRank().getPokerRank() - c2.getRank().getPokerRank();
		
	}
	public Rank getRank()
	{
		return rank;
	}
	
	public Suit getSuit() {
		return suit;
	}

	public boolean isMatch(Card c)
	{
		return this.suit.equals(c.suit) && this.rank.equals(c.rank);
	}
	
	public boolean isPair(Card c)
	{
		return this.rank.equals(c.rank);
	}
	
	public boolean isSuited(Card c)
	{
		return this.suit.equals(c.suit);
		
	}
	public String toString()
	{
		return rank.getIdentifier() + suit.getIdentifier();
	}
}
