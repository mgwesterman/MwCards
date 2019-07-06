package com.mw.games.cards.enums;

public enum Rank {

	ACE_LOW(1,1,"A"),
	TWO(2,2,"2"),
	THREE(3,3,"3"),
	FOUR(4,4,"4"),
	FIVE(5,5,"5"),
	SIX(6,6,"6"),
	SEVEN(7,7,"7"),
	EIGHT(8,8,"8"),
	NINE(9,9,"9"),
	TEN(10,10,"10"),
	JACK(10,11,"J"),
	QUEEN(10,12,"Q"),
	KING(10,13,"K"),
	ACE(11,14,"A");
	
	
	private final int blackJackRank;
	private final int pokerRank;
	private final String identifier;

    Rank(int aBlackJackRankValue, int aPokerRankValue, String aIdentifier) {
        this.blackJackRank = aBlackJackRankValue;
        this.pokerRank = aPokerRankValue;
        
        this.identifier = aIdentifier;
    }
    
    public int getBlackJackRank() {
        return this.blackJackRank;
    }

    public int getPokerRank() {
        return this.pokerRank;
    }

    public String getIdentifier() {
        return this.identifier;
    }
}
