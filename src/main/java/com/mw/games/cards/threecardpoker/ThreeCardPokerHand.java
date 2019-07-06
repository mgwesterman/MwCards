/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mw.games.cards.threecardpoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mw.games.cards.Card;
import com.mw.games.cards.Hand;
import com.mw.games.cards.enums.HandResult;

/**
 *
 * @author mwest
 */
public class ThreeCardPokerHand extends Hand {
    
    public static enum HandDescription {HIGH_CARD, PAIR, TRIPS, FLUSH, STRAIGHT, STRAIGHT_FLUSH}
    private final static List<HandDescription>  ORDERED_RANKS = new ArrayList<HandDescription>();
    static {
        ORDERED_RANKS.add(HandDescription.HIGH_CARD);
        ORDERED_RANKS.add(HandDescription.PAIR);
        ORDERED_RANKS.add(HandDescription.FLUSH);
        ORDERED_RANKS.add(HandDescription.STRAIGHT);
        ORDERED_RANKS.add(HandDescription.TRIPS);
        ORDERED_RANKS.add(HandDescription.STRAIGHT_FLUSH);
    }
    public void addCard(Card aCard)
    {
        cards.add(aCard);
    }
    
    public int numberOfCards()
    {
        return cards.size();
    }
    
    public void reveal()
    {
        System.out.println(cards);
    }
    
    private void sort()
    {
    	Collections.sort(cards);
    }
            
    public boolean isStraight()
    {
        if (cards.size() == 3)
        {
            if (matchingValues() == 0)
            {
                sort();
                int value1 = cards.get(0).getRank().getPokerRank();
                int value2 = cards.get(1).getRank().getPokerRank();
                int value3 = cards.get(2).getRank().getPokerRank();

                if ((value1-value2 == 1) && (value2-value3 == 1))
                    return true;
                // special case of A-2-3 being a straight
                else if ((value1 == 14) && (value2 == 3) && (value3 == 2))
                        return true;
                else    
                    return false;
            }
        }
        return false;
    }
    public boolean isFlush()
    {
        if (cards.size() == 3)
        {
            return cards.get(0).isSuited(cards.get(1)) && cards.get(0).isSuited(cards.get(2));
        }
        else return false;
    }

	
	public HandResult determineResult(Hand b)
	{
	    if (this.getValue() > b.getValue())
	            return HandResult.WIN;
        if (this.getValue() < b.getValue())
	            return HandResult.LOSE;
        else
	            return HandResult.PUSH; 
	}
	
	@Override
	public int getValue()
	{
		HandDescription aRank = rank();
	    int intA = ORDERED_RANKS.indexOf(aRank);
	     
	    StringBuffer sb = new StringBuffer();
	    // start the string with the value of the hand, then put the values of the cards.
	    // That should result in being able to successfully compare any two complete hands.
	    sb.append(String.format("%02d", intA));  		 
		for (Card c : cards)
		{
		    sb.append(String.format("%02d", c.getRank().getPokerRank()));  		 
		}
		return Integer.valueOf(sb.toString());
	}
	
    public Hand winner(Hand b)
    {
        if (this.getValue() > b.getValue())
            return this;
        else if (this.getValue() < b.getValue())
            return b;
        else
            System.out.println("not sure what to do if all three cards are equal");
        return null;
    }
    
    public boolean isPair()
    {
        return matchingValues() == 2;
    }

    public boolean isTrips()
    {
        return matchingValues() == 3;
    }
    public boolean isStraightFlush()
    {
        return isFlush() && isStraight();
    }
    
    private int matchingValues()
    {
        if (cards.size() == 3)
        {
            boolean match1 = cards.get(0).isPair(cards.get(1));
            boolean match2 = cards.get(0).isPair(cards.get(2));
            boolean match3 = cards.get(1).isPair(cards.get(2));
            
            if (!match1 && !match2 && !match3)
            {
                return 0;
            }
            else if (match1 && match2 && match3)
            {
                return 3;
            }
            else
            {
                return 2;
            }
        }
        return 0;
    }
    
    public HandDescription rank()
    {
        if (isStraightFlush())
            return HandDescription.STRAIGHT_FLUSH;

        if (isStraight())
            return HandDescription.STRAIGHT;

        if (isFlush())
            return HandDescription.FLUSH;
        
        int match = matchingValues();
        if (match == 0)
            return HandDescription.HIGH_CARD;

        if (match == 2)
            return HandDescription.PAIR;

        if (match == 3)
            return HandDescription.TRIPS;

        return null;
    }
    
    public String toString()
    {
        return rank() + ":" + cards;
                
    }

}
