package com.mw.games.cards;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Shuffler 
{
	List<Card> cards = new ArrayList<Card>();
	int placeInTheShoe = 0;
	int count = 0;
	int shuffleTriggerPoint = 80;
	
	public Shuffler(int deckCount)
	{
		for (int i=0; i<deckCount; i++)
		{
			Deck d = new Deck();
			for (int c=0; c<d.cardCount(); c++)
			{
				cards.add(d.getNext());
				count++;
			}
		}
		shuffle();
	}
	
	public Card getNext()
	{
		if (placeInTheShoe / count * 100 > shuffleTriggerPoint)
		{
			shuffle();
		}
		Card c = cards.get(placeInTheShoe);
		placeInTheShoe++;
		return c;				
	}
	public void shuffle()
	{
		Collections.shuffle(cards);
        placeInTheShoe = 0;
	}
	
}
