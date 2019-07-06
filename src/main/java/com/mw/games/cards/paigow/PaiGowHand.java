package com.mw.games.cards.paigow;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mw.games.cards.Card;
import com.mw.games.cards.Hand;

/**
 * a Pai Gow Hand is a specialization of a hand of playing cards.
 * By definition, it has 7 cards and no draws or discards. 
 * 
 * @author mw
 */
public class PaiGowHand extends Hand {

	public PaiGowHand(Card card1, Card card2,Card card3, Card card4,Card card5, Card card6, Card card7)
	{
		cards.clear();
		cards.add(card1);
		cards.add(card2);
		cards.add(card3);
		cards.add(card4);
		cards.add(card5);
		cards.add(card6);
		cards.add(card7);
	}
	
	@Override
	public int getValue()
	{
		int returnVal = 0;
		for (Card c : cards)
		{
			returnVal = returnVal + c.getRank().getPokerRank();
		}
		return returnVal;
	}
	
	public boolean isMatchingValue()
	{
		System.out.println("IsMatchingValue is not implemented yet");
		return false;
	}
	/**
	 * return a sorted version of this PaiGowHand
	 * @return
	 */
	public void sort()
	{
		if (cards != null)
		{
			Collections.sort(cards);
		}
		
	}
}
