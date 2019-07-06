package com.mw.games.cards;

import java.util.ArrayList;
import java.util.List;

public class Table {
	
	List<Player> players = new ArrayList<Player>();
	TableConfig config = new TableConfig();
	
	// add a shuffler to the table with a 6-deck shoe
	Shuffler shuffler = new Shuffler(6);
	Dealer dealer;
	
	int maxPlayers = 6;
	
	public void addPlayer(Player p) throws TableFullException
	{
		if (players.size() < maxPlayers)
		{
			players.add(p);
		}
		else
		{
			throw new TableFullException();
		}
	}
	public List<Player> getPlayers()
	{
		// should return a copy
		return players;
	}
	Dealer getDealer()
	{
		return dealer;
	}
	void setDealer(Dealer d)
	{
		dealer = d;
	}

	public int getTableMinBet()
	{
		return config.getTableMinBet();
	}
	public int getTableMaxBet()
	{
		return config.getTableMaxBet();
	}
	public int getBlackJackPayPercentage()
	{
		return config.getBlackJackPayPercentage();
	}
	Shuffler getShuffler()
	{
		return shuffler;
	}
	void trackResults(Player p, Hand dealerHand)
	{
		for (Hand h : p.getHands())
		{
	//		System.out.println(p + "," + p.getHands() + ",Dealer," + dealerHand + "," + 
	//			h.getHandResult() + "," + p.getBankroll());
		}
	}
}
