package com.mw.games.cards.threecardpoker;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mw.games.cards.Card;
import com.mw.games.cards.blackjack.BlackJackHand;
import com.mw.games.cards.blackjack.BlackJackHandEvaluator;
import com.mw.games.cards.enums.HandResult;
import com.mw.games.cards.enums.Rank;
import com.mw.games.cards.enums.Suit;

public class ThreeCardPokerHandTest {

	Card jackC = new Card(Suit.CLUBS,Rank.JACK);
	Card twoD = new Card(Suit.DIAMONDS,Rank.TWO);
	Card fourD = new Card(Suit.DIAMONDS,Rank.FOUR);
	Card aceS = new Card(Suit.SPADES,Rank.ACE);
	Card aceH = new Card(Suit.HEARTS,Rank.ACE);
	Card sixD = new Card(Suit.DIAMONDS,Rank.SIX);
	Card threeD = new Card(Suit.DIAMONDS,Rank.THREE);
	Card threeC = new Card(Suit.CLUBS,Rank.THREE);
	Card threeH = new Card(Suit.HEARTS,Rank.THREE);
	ThreeCardPokerHand h1 = new ThreeCardPokerHand(jackC,twoD,fourD); 
	ThreeCardPokerHand h2 = new ThreeCardPokerHand(jackC,twoD,fourD); 
	ThreeCardPokerHand pairOfAces = new ThreeCardPokerHand(jackC,aceS,aceH); 
	ThreeCardPokerHand lowerPairOfAces = new ThreeCardPokerHand(twoD,aceS,aceH); 
	ThreeCardPokerHand flush = new ThreeCardPokerHand(twoD,fourD,sixD); 
	ThreeCardPokerHand straight = new ThreeCardPokerHand(twoD,threeC,fourD); 
	ThreeCardPokerHand straightFlush = new ThreeCardPokerHand(twoD,threeD,fourD); 
	ThreeCardPokerHand trips = new ThreeCardPokerHand(threeD,threeC,threeH); 
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		assertEquals(h1.determineResult(h2),HandResult.PUSH);
		assertEquals(h2.determineResult(h1),HandResult.PUSH);
		assertEquals(pairOfAces.determineResult(h2),HandResult.WIN);
		assertEquals(pairOfAces.determineResult(lowerPairOfAces),HandResult.WIN);
		assertEquals(lowerPairOfAces.determineResult(pairOfAces),HandResult.LOSE);
		System.out.println(flush.getValue() + " vs " + pairOfAces.getValue());
		assertEquals(flush.determineResult(pairOfAces),HandResult.WIN);
		System.out.println(flush.getValue() + " vs " + straight.getValue());
		assertEquals(flush.determineResult(straight),HandResult.LOSE);
		assertEquals(straight.determineResult(pairOfAces),HandResult.WIN);
		assertEquals(straight.determineResult(straightFlush),HandResult.LOSE);
		assertEquals(straightFlush.determineResult(pairOfAces),HandResult.WIN);
		assertEquals(trips.determineResult(straight),HandResult.WIN);
		assertEquals(trips.determineResult(straightFlush),HandResult.LOSE);
		assertEquals(trips.determineResult(flush),HandResult.WIN);
	}

}
