package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import com.mw.games.cards.Deck;

public class Test {

	Deck d;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		d = new Deck();
	}

	@After
	public void tearDown() throws Exception {
	}

	@org.junit.Test
	public void test() {
		
		HandEvaluatorTest t = new HandEvaluatorTest();
		CardTest ct = new CardTest();
		HandTest ht = new HandTest();
		t.test();
		ct.test();
		ht.test();
	}

}
