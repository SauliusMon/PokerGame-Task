package com.saulius.pokergame.entities;

import java.util.*;

public class CardDeck {

    private final TreeSet<Card> deckOfCards;
    private final int maxDeckSize;

    public CardDeck(int maxDeckSize) {
        this.deckOfCards = new TreeSet<Card>();
        this.maxDeckSize = maxDeckSize;
    }

    public TreeSet<Card> getDeckOfCards() {
        return deckOfCards;
    }

    public int getMaxDeckSize() {
        return maxDeckSize;
    }

    public boolean addCard(Card card) {
        if (canAddCard()) {
            deckOfCards.add(card);
            return true;
        }
        return false;
    }

    public boolean canAddCard () {
        return deckOfCards.size() < maxDeckSize;
    }

    public boolean addCards(Card...cards) {
        if (canAddCards(cards.length)) {
            deckOfCards.addAll(Arrays.asList(cards));
            return true;
        }
        return false;
    }

    public boolean canAddCards (int cardsLength) {
        return deckOfCards.size() + cardsLength <= maxDeckSize;
    }

    @Override
    public String toString() {
        return "CardDeck{" +
                "deckOfCards=" + deckOfCards +
                ", maxDeckSize=" + maxDeckSize +
                '}';
    }
}
