package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mw.games.cards.Card;
import com.mw.games.cards.Hand;
import com.mw.games.cards.HandEvaluator;
import com.mw.games.cards.blackjack.BlackJackHand;
import com.mw.games.cards.blackjack.BlackJackHandEvaluator;
import com.mw.games.cards.enums.HandAction;
import com.mw.games.cards.enums.Rank;
import com.mw.games.cards.enums.Suit;

public class HandEvaluatorTest {

	Card c1 = new Card(Suit.CLUBS,Rank.JACK);
	Card c2 = new Card(Suit.DIAMONDS,Rank.TWO);
	Card c3 = new Card(Suit.DIAMONDS,Rank.FOUR);
	Card c4 = new Card(Suit.SPADES,Rank.ACE);
	Hand h1 = new BlackJackHand(c1,c2); // 12
	Hand h2 = new BlackJackHand(c2,c1); // 12
	Hand h3 = new BlackJackHand(c3,c1); // 14
	Hand h4 = new BlackJackHand(c4,c1); // 15
	Hand h5 = new BlackJackHand(c4,c4); // Aces
	Hand h6 = new BlackJackHand(c4,c1); // 21

	@Test
	public void test() 
	{
		HandEvaluator he = new BlackJackHandEvaluator();
		assertEquals(he.determineAction(h1,h2),HandAction.HIT); // 12 v dealer Jack
		assertEquals(he.determineAction(h1,h3),HandAction.HIT); // 14 v dealer Jack
		assertEquals(he.determineAction(h1,h5),HandAction.SPLIT); // Aces v dealer Jack
		assertEquals(he.determineAction(h1,h6),HandAction.STAND); // 21 v dealer Jack
		
		assertEquals(he.determineAction(h2,h1),HandAction.HIT); // 12 v dealer 2		
		assertEquals(he.determineAction(h2,h3),HandAction.STAND); // 14 v dealer 2
		assertEquals(he.determineAction(h2,h4),HandAction.STAND); // 15 v dealer 2		
		assertEquals(he.determineAction(h2,h5),HandAction.SPLIT); // Aces  v dealer 2		
		assertEquals(he.determineAction(h2,h6),HandAction.STAND); // 21  v dealer 2		
	}
}
