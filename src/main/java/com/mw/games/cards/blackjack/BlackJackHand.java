package com.mw.games.cards.blackjack;

import com.mw.games.cards.Card;
import com.mw.games.cards.Hand;

/**
 * a BlackHand is a specialization of a hand of playing cards.
 * By definition, it starts with 2 cards
 * 
 * @author mw
 */
public class BlackJackHand extends Hand {

	
	public BlackJackHand(Card card1, Card card2)
	{
		cards.clear();
		cards.add(card1);
		cards.add(card2);
	}
	
	@Override
	public int getValue()
	{
		int returnVal = 0;
		for (Card c : cards)
		{
			returnVal = returnVal + c.getRank().getBlackJackRank();
		}
		return returnVal;
	}

	public boolean isBlackJack()
	{
		return cardCount() == 2 && this.getValue() == 21;
	}
	public boolean isMatchingValue()
	{
		return cards.get(0).getRank().getBlackJackRank() == cards.get(1).getRank().getBlackJackRank();
	}

	public String getStartingHand()
	{
		return "[" + getCard(0).getRank().getBlackJackRank() + "," + getCard(1).getRank().getBlackJackRank() + "]";
	}
}
