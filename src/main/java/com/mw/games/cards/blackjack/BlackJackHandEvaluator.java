package com.mw.games.cards.blackjack;

import java.util.HashMap;
import java.util.Map;

import com.mw.games.cards.Card;
import com.mw.games.cards.Hand;
import com.mw.games.cards.HandEvaluator;
import com.mw.games.cards.enums.HandAction;
import com.mw.games.cards.enums.HandResult;

import static com.mw.games.cards.enums.HandAction.HIT;
import static com.mw.games.cards.enums.HandAction.STAND;
import static com.mw.games.cards.enums.HandAction.DOUBLE;
import static com.mw.games.cards.enums.HandAction.SPLIT;
import static com.mw.games.cards.enums.HandAction.SURRENDER;


import com.mw.games.cards.enums.Rank;

/**
 * Start with a default BlackJack hand strategy, represented as arrays of HandAction values.
 * Allow for extension and modification, so representations of different strategies can be created
 *  by overriding values in the decision table.
 *  
 * @author M Westerman  7/2019
 *
 */
public class BlackJackHandEvaluator extends HandEvaluator {

	private Map<Integer, HandAction[]> actions 	= new HashMap<Integer,HandAction[]>();
	private Map<Integer, HandAction[]> aceActions = new HashMap<Integer,HandAction[]>();
	private Map<Integer, HandAction[]> splitActions = new HashMap<Integer,HandAction[]>();
	
	private HandAction[] actionsPlayerA2 = {null,null,HIT,HIT,HIT,DOUBLE,DOUBLE,HIT,HIT,HIT,HIT,HIT};
	private HandAction[] actionsPlayerA3 = {null,null,HIT,HIT,HIT,DOUBLE,DOUBLE,HIT,HIT,HIT,HIT,HIT};
	private HandAction[] actionsPlayerA4 = {null,null,HIT,HIT,DOUBLE,DOUBLE,DOUBLE,HIT,HIT,HIT,HIT,HIT};
	private HandAction[] actionsPlayerA5 = {null,null,HIT,HIT,DOUBLE,DOUBLE,DOUBLE,HIT,HIT,HIT,HIT,HIT};
	private HandAction[] actionsPlayerA6 = {null,null,HIT,DOUBLE,DOUBLE,DOUBLE,DOUBLE,HIT,HIT,HIT,HIT,HIT};
	private HandAction[] actionsPlayerA7 = {null,null,STAND,DOUBLE,DOUBLE,DOUBLE,DOUBLE,STAND,STAND,HIT,HIT,HIT};
	private HandAction[] actionsPlayerA8 = {null,null,STAND,STAND,STAND,DOUBLE,STAND,STAND,STAND,STAND,STAND,STAND};
	private HandAction[] actionsPlayerA9 = {null,null,STAND,STAND,STAND,STAND,STAND,STAND,STAND,STAND,STAND,STAND};
	private HandAction[] actionsPlayerA10 = {null,null,STAND,STAND,STAND,STAND,STAND,STAND,STAND,STAND,STAND,STAND};
	private HandAction[] actionsPlayerAA = {null,null,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT};

	private HandAction[] actionsPlayer9 = {null,null,HIT,DOUBLE,DOUBLE,DOUBLE,DOUBLE,HIT,HIT,HIT,HIT,HIT};
	private HandAction[] actionsPlayer10 = {null,null,DOUBLE,DOUBLE,DOUBLE,DOUBLE,DOUBLE,DOUBLE,DOUBLE,DOUBLE,HIT,HIT};
	private HandAction[] actionsPlayer11 = {null,null,DOUBLE,DOUBLE,DOUBLE,DOUBLE,DOUBLE,DOUBLE,DOUBLE,DOUBLE,DOUBLE,HIT};
	private HandAction[] actionsPlayer12 = {null,null,HIT,HIT,STAND,STAND,STAND,HIT,HIT,HIT,HIT,HIT};
	private HandAction[] actionsPlayer13 = {null,null,STAND,STAND,STAND,STAND,STAND,HIT,HIT,HIT,HIT,HIT};
	private HandAction[] actionsPlayer14 = {null,null,STAND,STAND,STAND,STAND,STAND,HIT,HIT,HIT,HIT,HIT};
	private HandAction[] actionsPlayer15 = {null,null,STAND,STAND,STAND,STAND,STAND,HIT,HIT,HIT,SURRENDER,HIT};
	private HandAction[] actionsPlayer16 = {null,null,STAND,STAND,STAND,STAND,STAND,HIT,HIT,SURRENDER,SURRENDER,SURRENDER};

	private HandAction[] splitPlayer2s = {null,null,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,HIT,HIT,HIT,HIT};
	private HandAction[] splitPlayer3s = {null,null,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,HIT,HIT,HIT,HIT};
	private HandAction[] splitPlayer4s = {null,null,HIT,HIT,HIT,SPLIT,SPLIT,HIT,HIT,HIT,HIT,HIT};
	private HandAction[] splitPlayer5s = {null,null,DOUBLE,DOUBLE,DOUBLE,DOUBLE,DOUBLE,DOUBLE,DOUBLE,DOUBLE,HIT,HIT};
	private HandAction[] splitPlayer6s = {null,null,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,HIT,HIT,HIT,HIT,HIT};
	private HandAction[] splitPlayer7s = {null,null,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,HIT,HIT,HIT,HIT};
	private HandAction[] splitPlayer8s = {null,null,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT};
	private HandAction[] splitPlayer9s = {null,null,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,STAND,SPLIT,SPLIT,STAND,STAND};
	private HandAction[] splitPlayer10s = {null,null,STAND,STAND,STAND,STAND,STAND,STAND,STAND,STAND,STAND,STAND};
	private HandAction[] splitPlayerAces = {null,null,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT,SPLIT};

	public BlackJackHandEvaluator()
	{
		
		actions.put(9,actionsPlayer9);
		actions.put(10,actionsPlayer10);
		actions.put(11,actionsPlayer11);
		actions.put(12,actionsPlayer12);
		actions.put(13,actionsPlayer13);
		actions.put(14,actionsPlayer14);
		actions.put(15,actionsPlayer15);
		actions.put(16,actionsPlayer16);
		
		aceActions.put(13,actionsPlayerA2);
		aceActions.put(14,actionsPlayerA3);
		aceActions.put(15,actionsPlayerA4);
		aceActions.put(16,actionsPlayerA5);
		aceActions.put(17,actionsPlayerA6);
		aceActions.put(18,actionsPlayerA7);
		aceActions.put(19,actionsPlayerA8);
		aceActions.put(20,actionsPlayerA9);
		aceActions.put(21,actionsPlayerA10);
		aceActions.put(22,actionsPlayerAA);

		splitActions.put(4,splitPlayer2s);
		splitActions.put(6,splitPlayer3s);
		splitActions.put(8,splitPlayer4s);
		splitActions.put(10,splitPlayer5s);
		splitActions.put(12,splitPlayer6s);
		splitActions.put(14,splitPlayer7s);
		splitActions.put(16,splitPlayer8s);
		splitActions.put(18,splitPlayer9s);
		splitActions.put(20,splitPlayer10s);
		splitActions.put(22,splitPlayerAces);
	}

	@Override
	public HandAction determineAction(Hand dealerHand, Hand playerHand)
	{
		//System.out.println(dealerHand + ", vs player--" + playerHand);
		
		Card upCard = dealerHand.getFirstCard();
		int upCardValue = upCard.getRank().getBlackJackRank();
		int playerHandValue = playerHand.getValue();

		if (playerHand.cardCount() == 2)
		{
			HandAction[] handActions = null;
			if (playerHand.isMatchingValue())
			{
				handActions = splitActions.get(playerHandValue);
				HandAction ha = handActions[upCardValue];
				playerHand.setHandAction(ha);
				return ha;
			}

			if (playerHand.containsAce())
			{
				handActions = aceActions.get(playerHandValue);
				HandAction ha = handActions[upCardValue];
				playerHand.setHandAction(ha);
				return ha;
			}
		}
		
		if (playerHandValue > 21 && playerHand.containsAce())
		{
			// convert the Ace from 11 to 1 and keep playing
			int aceSlot = playerHand.getAcePosition();
			Card c = playerHand.getCard(aceSlot);
			playerHand.setCard(aceSlot, new Card(c.getSuit(),Rank.ACE_LOW));
			return determineAction(dealerHand, playerHand);
		}

		if (playerHandValue >= 17)
			return STAND;

		if (playerHandValue < 9)
			return HIT;
		
		HandAction[] handActions = actions.get(playerHandValue);
		HandAction ha = handActions[upCardValue];
		
		if (playerHand.cardCount() > 2)
		{
			if (ha.equals(DOUBLE) || ha.equals(SURRENDER)) return HIT;
		}
		//System.out.println(ha);
		return ha;
	}	
	
	@Override
	public HandResult determineResult(Hand playerHand, Hand dealerHand)
	{
		if (playerHand.getHandResult() != null)
		{
			return playerHand.getHandResult();
		}
		// if the dealer hand is a two-card 21, all lose
		if (dealerHand.getValue() == 21 && dealerHand.cardCount() == 2)
		{
			playerHand.setHandResult(HandResult.LOSE);
			return HandResult.LOSE;
		}
		// if the player busts, they lose
		if (playerHand.getValue() > 21)
		{
			playerHand.setHandResult(HandResult.LOSE);
			return HandResult.LOSE;
		}
		// otherwise, if dealer busts, win
		if (dealerHand.getValue() > 21)
		{
			playerHand.setHandResult(HandResult.WIN);
			return HandResult.WIN;
		}
		return super.determineResult(playerHand,dealerHand);
	}

	public boolean isBlackJack(Hand aHand)
	{
		return aHand.cardCount() == 2 && aHand.getValue() == 21;
	}
}
