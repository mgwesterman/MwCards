package com.mw.games.cards;

import java.util.List;

import com.mw.games.cards.blackjack.BlackJackHand;
import com.mw.games.cards.blackjack.BlackJackHandEvaluator;
import com.mw.games.cards.blackjack.DealerHandEvaluator;
import com.mw.games.cards.enums.HandAction;
import com.mw.games.cards.enums.HandResult;

public class Dealer extends Player {
	
	public Dealer(String name)
	{
		super(name);
	}
	
	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}
	private Table table;
	private DealerHandEvaluator dhe = new DealerHandEvaluator();
	private BlackJackHandEvaluator bhe = new BlackJackHandEvaluator();
	
	public void dealBlackJack(int numHands)
	{
		int k=0;
		while (k<numHands)
		{			
			dealABlackJackHand();
			k++;
		}	
	}
	
	public void dealABlackJackHand()
	{
		Shuffler s = table.getShuffler();
		
		Hand dealerHand = new BlackJackHand(s.getNext(),s.getNext());
		dealABlackJackHand(dealerHand);
	}
	
	public void dealABlackJackHand(Hand dealerHand)
	{
		Shuffler s = table.getShuffler();

		addHand(dealerHand);
		List<Player> players = table.getPlayers();
		for (Player p : players)
		{
			Hand h = new BlackJackHand(s.getNext(), s.getNext());
			p.placeBet(h);
			p.addHand(h);
		}
			
		for (Player p : players)
		{
			if (dealerHand.getValue() != 21)
			{
				p.evaluateHands(dealerHand);
				p.playHands(this);
			}
		}
		this.playDealerHand();

		for (Player p : players)
		{
			payBets(p);
		}

		//clear the table
		for (Player p : players)
		{
			table.trackResults(p, dealerHand);
			p.clearHands();
		} 
		this.clearHands();
	}

	
	private void payBets(Player p)
	{
		List<Hand> hands = p.getHands();
		
		for (int i=0; i<hands.size(); i++)
		{
			BlackJackHand playerHand = (BlackJackHand)hands.get(i);
			HandResult hr = bhe.determineResult(playerHand,getDealerHand());
			switch(hr)
			{
				case BLACKJACK:
					p.win(playerHand.getBet() * table.getBlackJackPayPercentage() / 100);
					break;
				case WIN:
					p.win(playerHand.getBet());
					break;
				case SURRENDER:
					p.lose(playerHand.getBet() / 2);
					break;
				case LOSE:
					p.lose(playerHand.getBet());
					break;
				case PUSH:
					p.push(playerHand.getBet());
					break;
			}	
			table.trackResults(p,getDealerHand());
		}
	}
	
	public Card dealACard()
	{
		return table.getShuffler().getNext();
	}
	
	private void playDealerHand()
	{
		HandAction ha = dhe.determineAction(getDealerHand());
		while (!ha.equals(HandAction.STAND))
		{
			getDealerHand().addCard(dealACard());
			ha = dhe.determineAction(getDealerHand());
		}
	}
	public Hand getDealerHand()
	{
		return getHand(0);
	}
}
