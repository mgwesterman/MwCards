package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mw.games.cards.Card;
import com.mw.games.cards.Dealer;
import com.mw.games.cards.Hand;
import com.mw.games.cards.Player;
import com.mw.games.cards.Table;
import com.mw.games.cards.blackjack.BlackJackHand;
import com.mw.games.cards.enums.HandAction;
import com.mw.games.cards.enums.Rank;
import com.mw.games.cards.enums.Suit;

public class PlayerTest {

	@Test
	public void testEvaluateHand() {
		Player p = new Player("1");
		
		Hand playerHand = new BlackJackHand(new Card(Suit.HEARTS,Rank.SIX), new Card(Suit.DIAMONDS,Rank.ACE));
		Hand dealerHand = new BlackJackHand(new Card(Suit.CLUBS,Rank.NINE), new Card(Suit.SPADES,Rank.ACE));
		assertEquals(p.evaluateHand(dealerHand, playerHand), HandAction.HIT);
		
		playerHand.addCard(new Card(Suit.SPADES,Rank.NINE));
		assertEquals(p.evaluateHand(dealerHand, playerHand), HandAction.HIT);
		
		 playerHand = new BlackJackHand(new Card(Suit.HEARTS,Rank.ACE), new Card(Suit.DIAMONDS,Rank.ACE));
		 dealerHand = new BlackJackHand(new Card(Suit.CLUBS,Rank.NINE), new Card(Suit.SPADES,Rank.ACE));
		assertEquals(p.evaluateHand(dealerHand, playerHand), HandAction.SPLIT);

		playerHand = new BlackJackHand(new Card(Suit.HEARTS,Rank.NINE), new Card(Suit.DIAMONDS,Rank.NINE));
		 dealerHand = new BlackJackHand(new Card(Suit.CLUBS,Rank.TEN), new Card(Suit.SPADES,Rank.TWO));
		 // stand on 18 against a 7, 10, or 11
		assertEquals(p.evaluateHand(dealerHand, playerHand), HandAction.STAND);

		playerHand = new BlackJackHand(new Card(Suit.HEARTS,Rank.NINE), new Card(Suit.DIAMONDS,Rank.NINE));
		 dealerHand = new BlackJackHand(new Card(Suit.CLUBS,Rank.FIVE), new Card(Suit.SPADES,Rank.FIVE));
		 // split 18 against a bad card
		assertEquals(p.evaluateHand(dealerHand, playerHand), HandAction.SPLIT);

		playerHand = new BlackJackHand(new Card(Suit.HEARTS,Rank.TEN), new Card(Suit.DIAMONDS,Rank.JACK));
		 dealerHand = new BlackJackHand(new Card(Suit.CLUBS,Rank.FIVE), new Card(Suit.SPADES,Rank.FIVE));
		 // don't split 10's
		assertEquals(p.evaluateHand(dealerHand, playerHand), HandAction.STAND);
	}

	@Test
	public void testPlayHand() {
		Player p = new Player("1");
		Dealer d = new Dealer("Bob");
		
		Hand playerHand = new BlackJackHand(new Card(Suit.HEARTS,Rank.NINE), new Card(Suit.DIAMONDS,Rank.NINE));
		Hand dealerHand = new BlackJackHand(new Card(Suit.CLUBS,Rank.SIX), new Card(Suit.SPADES,Rank.TEN));
		d.addHand(dealerHand);
		p.addHand(playerHand);
		p.playHands(d);
		// playHands doesn't lend itself to checking a result.  at least check it split the hands
		assertFalse(p.getHands().size() == 1);

		p.clearHands();
		d.clearHands();
		
		playerHand = new BlackJackHand(new Card(Suit.HEARTS,Rank.ACE), new Card(Suit.DIAMONDS,Rank.ACE));
		dealerHand = new BlackJackHand(new Card(Suit.CLUBS,Rank.SEVEN), new Card(Suit.SPADES,Rank.TEN));
		d.addHand(dealerHand);
		p.addHand(playerHand);
	
		p.playHands(d);
		// playHands doesn't lend itself to checking a result.  at least check it split the hands
		assertTrue(p.getHand(0).containsAce() && p.getHand(1).containsAce());
	}
}
