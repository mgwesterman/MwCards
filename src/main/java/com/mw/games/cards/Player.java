package com.mw.games.cards;

import java.util.ArrayList;
import java.util.List;

import com.mw.games.cards.blackjack.BlackJackHand;
import com.mw.games.cards.blackjack.BlackJackHandEvaluator;
import com.mw.games.cards.enums.HandAction;
import com.mw.games.cards.enums.HandResult;
import com.mw.games.cards.enums.Rank;

public class Player {

	@Override
	public String toString() {
		return "Player " + name;
	}

	List<Hand> hands = new ArrayList<Hand> (); 
	BlackJackHandEvaluator he = new BlackJackHandEvaluator();
	BetEvaluator be = new BetEvaluator();
	int bankroll = 100;
	int lastBet = 0;
	private int wins, losses, pushes, blackjacks, surrenders;
	
	public void clearStats()
	{
		setWins(0);
		setLosses(0);
		setPushes(0);
		setBlackjacks(0);
		setSurrenders(0);
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public int getPushes() {
		return pushes;
	}
	public void setPushes(int pushes) {
		this.pushes = pushes;
	}
	public int getBlackjacks() {
		return blackjacks;
	}
	public void setBlackjacks(int blackjacks) {
		this.blackjacks = blackjacks;
	}
	public int getSurrenders() {
		return surrenders;
	}
	public void setSurrenders(int surrenders) {
		this.surrenders = surrenders;
	}

	String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Player(String aName)
	{
		name = aName;
	}
	public int getBankroll() {
		return bankroll;
	}
	public void setBankroll(int bankroll) {
		this.bankroll = bankroll;
	}
	public void addHand(Hand aHand)
	{
		hands.add(aHand);
	}
	public void clearHands()
	{
		hands.clear();
	}
	
	public HandAction evaluateHand(Hand dealerHand, Hand playerHand)
	{
		return he.determineAction(dealerHand, playerHand);
	}
	
	public void evaluateHands(Hand dealerHand)
	{
		for (Hand h : hands) 
		{
			HandAction ha = he.determineAction(dealerHand, h);
			h.setHandAction(ha);
		}
	}
	public Hand getHand(int i)
	{
		return hands.get(i); 
	}
	public List<Hand> getHands()
	{
		return hands; 
	}
	
	void placeBet(Hand aHand)
	{
		aHand.setBet(be.getNextBet(lastBet));
	}
	
	void win(int aBet)
	{
		bankroll = bankroll + aBet;
		wins++;
	}
	
	void lose(int aBet)
	{
		bankroll = bankroll - aBet;
		losses++;
	}
	void push(int aBet)
	{
		pushes++;
	}
	
	public void playHands(Dealer d)
	{
		for (int i=0; i<hands.size(); i++)
		{
			Hand playerHand = hands.get(i);
			Hand dealerHand = d.getHand(0);
			HandAction ha = playerHand.getHandAction();
			if (ha == null)
			{
				ha = evaluateHand(dealerHand, playerHand);
			}
			
			if (he.isBlackJack(playerHand))
			{
				blackjacks++;
			}
			if (ha.equals(HandAction.SPLIT))
			{
				//System.out.println("**** SPLITTING " + playerHand);
				Hand newHand = new BlackJackHand(playerHand.getCard(1),d.dealACard());
				//System.out.println("newHand:" + newHand);
				playerHand = new BlackJackHand(playerHand.getFirstCard(),d.dealACard());
				hands.set(i, playerHand);
				//System.out.println("playerHand:" + playerHand);
				addHand(newHand);
				// if splitting aces, you only get one card on each
				if (playerHand.getFirstCard().getRank().equals(Rank.ACE))
				{
					playerHand.setMaxCards(2);
					newHand.setMaxCards(2);
				}
				this.evaluateHands(dealerHand);
				playHands(d);
			}
			
			// hit until it's timne to stand or you hit a max (like on double down or split aces)
			while (!ha.equals(HandAction.STAND) && playerHand.cardCount() < playerHand.getMaxCards())
			{
				switch (ha) 
				{
					case HIT:
						playerHand.addCard(d.dealACard());
						playerHand.setHandAction(this.evaluateHand(dealerHand, playerHand));
						break;
					case DOUBLE:
						playerHand.addCard(d.dealACard());
						playerHand.setMaxCards(3);
						break;
					case SPLIT:
						playerHand.setHandAction(HandAction.STAND);
						break; // should've been handled above
					case SURRENDER:
						playerHand.setHandResult(HandResult.SURRENDER);
						playerHand.setMaxCards(2);
						surrenders++;
						break;
					case STAND:
						break;
				}
				ha = playerHand.getHandAction();
			}
		}
	}
}
