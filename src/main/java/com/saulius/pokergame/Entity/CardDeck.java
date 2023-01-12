package com.saulius.pokergame.Entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardDeck {

    private final List<Card> deckOfCards;

    public CardDeck() {
        deckOfCards = new ArrayList<>(5);
    }

    public List<Card> getDeckOfCards() {
        return deckOfCards;
    }

    public void addCard(Card card) {
        deckOfCards.add(card);
    }

    public void addCards(Card...cards) {
        deckOfCards.addAll(Arrays.asList(cards));
    }
}
