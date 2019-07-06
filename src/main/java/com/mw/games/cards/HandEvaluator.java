package com.mw.games.cards;

import com.mw.games.cards.enums.HandAction;
import com.mw.games.cards.enums.HandResult;

public abstract class HandEvaluator {

	public abstract HandAction determineAction(Hand dealerHand, Hand playerHand);
	/*
	 * compare two hands from the perspective of the first hand
	 */
	public HandResult determineResult(Hand h1, Hand h2)
	{
		if (h1.getValue() > h2.getValue())
		{
			h1.setHandResult(HandResult.WIN);
			return HandResult.WIN;
		}
		else if (h1.getValue() < h2.getValue())
		{
			h1.setHandResult(HandResult.LOSE);
			return HandResult.LOSE;
		}
		else
		{
			h1.setHandResult(HandResult.PUSH);
			return HandResult.PUSH;
		}
	}

}
