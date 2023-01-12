package com.saulius.pokergame.entities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CardDeck {

    private final List<Card> deckOfCards;
    private final int maxDeckSize;

    public CardDeck(int maxDeckSize) {
        this.deckOfCards = new ArrayList<>(maxDeckSize);
        this.maxDeckSize = maxDeckSize;
    }

    public List<Card> getDeckOfCards() {
        return deckOfCards;
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
