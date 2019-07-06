package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import com.mw.games.cards.Card;
import com.mw.games.cards.blackjack.BlackJackHand;
import com.mw.games.cards.blackjack.BlackJackHandEvaluator;
import com.mw.games.cards.enums.HandResult;
import com.mw.games.cards.enums.Rank;
import com.mw.games.cards.enums.Suit;

public class HandTest {

	Card c1 = new Card(Suit.CLUBS,Rank.JACK);
	Card c2 = new Card(Suit.DIAMONDS,Rank.TWO);
	Card c3 = new Card(Suit.DIAMONDS,Rank.FOUR);
	Card c4 = new Card(Suit.SPADES,Rank.ACE);
	BlackJackHand h1 = new BlackJackHand(c1,c2); // 12
	BlackJackHand h2 = new BlackJackHand(c2,c1); // 12
	BlackJackHand h3 = new BlackJackHand(c3,c1); // 14
	BlackJackHand h4 = new BlackJackHand(c4,c1); // 15
	BlackJackHand h5 = new BlackJackHand(c4,c4); // 12
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@Test
	public void test() {
		BlackJackHandEvaluator bhe = new BlackJackHandEvaluator();
		assertEquals(bhe.determineResult(h1, h2),HandResult.PUSH);
		assertNotEquals(bhe.determineResult(h3, h2),HandResult.PUSH);
		assertEquals(bhe.determineResult(h3, h2),HandResult.WIN);
		assertFalse(h1.containsAce());
		assertTrue(h5.containsAce());
		assertTrue(h4.containsAce());
		
	}

}
