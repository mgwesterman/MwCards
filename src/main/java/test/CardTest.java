package test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.mw.games.cards.Card;
import com.mw.games.cards.enums.Rank;
import com.mw.games.cards.enums.Suit;

public class CardTest {

	@Test
	public void test() {
		
		Card clubs8  = new Card(Suit.CLUBS, Rank.EIGHT);
		Card clubs82 = new Card(Suit.CLUBS, Rank.EIGHT);
		Card clubs9  = new Card(Suit.CLUBS,Rank.NINE);
		Card hearts8 = new Card(Suit.HEARTS, Rank.EIGHT);
		
		assertTrue(testMatch(clubs8,clubs82));
		assertTrue(testMatch(clubs82,clubs8));
		assertFalse(testMatch(clubs8,clubs9));
		assertFalse(testMatch(hearts8,clubs8));

		assertTrue(testPair(clubs8,clubs82));
		assertTrue(testPair(clubs82,clubs8));
		assertFalse(testPair(clubs8,clubs9));
		assertTrue(testPair(hearts8,clubs8));

		assertTrue(testSuited(clubs8,clubs82));
		assertTrue(testSuited(clubs82,clubs8));
		assertTrue(testSuited(clubs8,clubs9));
		assertFalse(testSuited(hearts8,clubs8));

		assertEquals(testCompareTo(clubs8,clubs82),0);
		assertEquals(testCompareTo(clubs9,clubs82),1);
		assertEquals(testCompareTo(hearts8,clubs82),2);
		assertEquals(testCompareTo(clubs8,hearts8),-2);
		
	}
	
	public boolean testMatch(Card c1, Card c2)
	{
		return c1.isMatch(c2);
	}
	public boolean testPair(Card c1, Card c2)
	{
		return c1.isPair(c2);
	}
	public boolean testSuited(Card c1, Card c2)
	{
		return c1.isSuited(c2);
	}
	public int testCompareTo(Card c1, Card c2)
	{
		return c1.compareTo(c2);
	}

}
