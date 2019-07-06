package com.mw.games.cards;

import com.mw.games.cards.blackjack.BlackJackHand;
import com.mw.games.cards.enums.Rank;
import com.mw.games.cards.enums.Suit;

public class BlackJackGame {
	
	static int count = 1;
	Table t;
	
	public static void main(String[] args)
	{
		if (args.length > 0)
			count = Integer.valueOf(args[0]).intValue();
		else
			count = 1;
	
		BlackJackGame game = new BlackJackGame();
		game.setTheTable();
		game.runABlackJackSession(count);
		game.runWithFixedDealerUpcard(count);
		
		System.out.println("DONE");
	}

	private Table setTheTable()
	{
		t = new Table();
		try
		{
			t.addPlayer(new Player("1"));
		//	t.addPlayer(new Player("2"));
		//	t.addPlayer(new Player("3"));
		//	t.addPlayer(new Player("4"));
		//	t.addPlayer(new Player("5"));
		}
		catch (TableFullException tfe)
		{
			System.err.println("Too many players");
		}
		return t;
	}

	private void runABlackJackSession(int count)
	{
		if (t == null)
			t = setTheTable();
		
		Dealer d = new Dealer("Dealer");
		d.setTable(t);
		t.setDealer(d);
		t.getDealer().dealBlackJack(count);
		
		for (Player p : t.getPlayers())
		{
			int totalHands = p.getWins() + p.getLosses() + p.getPushes();
			System.out.println(p + ":W/L/P/BJ/S:" + p.getWins() + "/" + p.getLosses() + "/" + p.getPushes() + "/" + p.getBlackjacks() + "/" + p.getSurrenders());
			System.out.println(p + "Total Hands:" + totalHands);
		}
	}

	private void runWithFixedDealerUpcard(int count)
	{
		Table t = setTheTable();
		
		Dealer d = new Dealer("Dealer");
		d.setTable(t);
		t.setDealer(d);
		
		
		for (Rank aRank : Rank.values())
		{
			System.out.println("Dealer Upcard is:" + aRank);
			if (!aRank.equals(Rank.ACE_LOW))
			{
				int i=0;
				while (i<count)
				{
					{
						Hand dealerHand = new BlackJackHand(new Card(Suit.CLUBS,aRank), t.getShuffler().getNext());
						d.dealABlackJackHand(dealerHand);
						i++;
					}
				}
			
				for (Player p : t.getPlayers())
				{
					int totalHands = p.getWins() + p.getLosses() + p.getPushes();
					System.out.println("Hands dealt:" + count);
					System.out.println(p + "Total Hands:" + totalHands);
					System.out.println("Blackjacks %" + p.getBlackjacks());
					System.out.println("Surrenders %" + p.getSurrenders());
					
					System.out.println(p + ":W/L/P:" + p.getWins() + "/" + p.getLosses() + "/" + p.getPushes());
					p.clearStats();
				}
			}
		}
	}
}
