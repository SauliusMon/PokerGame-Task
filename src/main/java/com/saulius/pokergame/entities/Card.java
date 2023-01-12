package com.saulius.pokergame.entities;

import com.saulius.pokergame.enums.CardSuit;

public class Card {

    private final int cardValue;
    private final CardSuit cardSuit;

    public Card(int cardValue, CardSuit cardSuit) {
        this.cardValue = cardValue;
        this.cardSuit = cardSuit;
    }

    public int getCardValue() {
        return cardValue;
    }

    public CardSuit getCardSuit() {
        return cardSuit;
    }

    @Override
    public String toString() {
        return cardValue + cardSuit.toString();
    }
}
