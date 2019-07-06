package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.mw.games.cards.Card;
import com.mw.games.cards.enums.Rank;
import com.mw.games.cards.enums.Suit;
import com.mw.games.cards.paigow.PaiGowHand;

public class PaiGowHandTest {

	PaiGowHand hand;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		 hand = new PaiGowHand( 
				new Card(Suit.CLUBS,Rank.NINE),
				new Card(Suit.CLUBS,Rank.SEVEN),
				new Card(Suit.DIAMONDS,Rank.FIVE),
				new Card(Suit.CLUBS,Rank.THREE),
				new Card(Suit.SPADES,Rank.ACE),
				new Card(Suit.CLUBS,Rank.QUEEN),
				new Card(Suit.HEARTS,Rank.JACK));
				
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testSort() {
		
		hand.sort();
	//	System.out.println(hand);
		assertEquals(hand.getCard(6).getRank(),Rank.ACE);
		assertEquals(hand.getCard(5).getRank(),Rank.QUEEN);
		assertEquals(hand.getCard(4).getRank(),Rank.JACK);
		assertEquals(hand.getCard(3).getRank(),Rank.NINE);
		assertEquals(hand.getCard(2).getRank(),Rank.SEVEN);
		assertEquals(hand.getCard(1).getRank(),Rank.FIVE);
		assertEquals(hand.getCard(0).getRank(),Rank.THREE);
	}

}
