package com.mw.games.cards;

import com.mw.games.cards.enums.HandAction;
import com.mw.games.cards.enums.Rank;

public class DealerHandEvaluator {

	public HandAction determineAction(Hand dealerHand)
	{
		int handValue = dealerHand.getValue();
		
		if (handValue > 21)
		{
			if (dealerHand.containsAce())
			{
				int aceSlot = dealerHand.getAcePosition();
				Card c = dealerHand.getCard(aceSlot);
				dealerHand.setCard(aceSlot, new Card(c.getSuit(),Rank.ACE_LOW));
				determineAction(dealerHand);
			}
		}
		if (handValue > 17)
		{
			return HandAction.STAND;
		}
		if (handValue < 17)
		{
			return HandAction.HIT;
		}
		else 
		{
			if (dealerHand.containsAce())   // dealer hits on soft 17
			{
				return HandAction.HIT;
			}
		}
		return HandAction.STAND;
	}	
}
