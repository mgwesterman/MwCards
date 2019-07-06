package com.mw.games.cards;

import java.util.ArrayList;
import java.util.List;

import com.mw.games.cards.enums.HandAction;
import com.mw.games.cards.enums.HandResult;
import com.mw.games.cards.enums.Rank;

public abstract class Hand {
	
	protected List<Card> cards = new ArrayList<Card>(); 

	private HandAction handAction;
	private HandResult handResult;

	private int bet;
	private int maxCards = 10; // arbitrarily large value
	
	public void addCard(Card aCard)
	{
		cards.add(aCard);
	}

	public int cardCount()
	{
		return cards.size();
	}

	public boolean containsAce()
	{
		for (int i=0; i<cards.size(); i++) 
		{
			if (cards.get(i).getRank().equals(Rank.ACE))
			{
				return true;
			}
		}
		return false;
	}

	public int getAcePosition()
	{
		int i = 0;
		for (Card c : cards)
		{
			if (c.getRank() == Rank.ACE)
			{
				return i;
			}
			i++;
		}
		return -1;
	}
	
	public int getBet()
	{
		return bet;
	}
	

	public Card getCard(int i) 
	{
		return cards.get(i);
	}
	
	public Card getFirstCard() 
	{
		return cards.get(0);
	}
	
	public HandAction getHandAction()
	{
		return handAction;
	}
	
	public HandResult getHandResult() {
		return handResult;		
	}
	
	public int getMaxCards() {
		return maxCards;
	}
	
	abstract public int getValue();
	
	public boolean isPair()
	{
		return cards.get(0).isPair(cards.get(1));
	}
	
	public void setBet(int aBet)
	{
		bet = aBet;
	}
	
	// this could be used when changing an Ace from high to low
	public void setCard(int i, Card aCard) 
	{
		cards.set(i, aCard);
	}
	public void setHandAction(HandAction ha)
	{
		handAction = ha;
	}
	public void setHandResult(HandResult hr) {
		handResult = hr;		
	}
	public void setMaxCards(int maxCards) {
		this.maxCards = maxCards;
	}
	@Override
	public String toString()
	{
		StringBuffer sb = new StringBuffer();
		for (int i=0; i<cards.size(); i++)
		{
			sb.append(cards.get(i)).append(" ");
		}
		sb.append(":" + this.getValue());
		return sb.toString();
	}
}