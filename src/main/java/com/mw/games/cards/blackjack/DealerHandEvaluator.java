package com.mw.games.cards.blackjack;

import com.mw.games.cards.Card;
import com.mw.games.cards.Hand;
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
		
		if (handValue < 17) return HandAction.HIT;
		if (handValue == 17 && dealerHand.containsAce())   // dealer stands on soft 17	
			return HandAction.STAND;
		
		return HandAction.STAND;
	}	
}
