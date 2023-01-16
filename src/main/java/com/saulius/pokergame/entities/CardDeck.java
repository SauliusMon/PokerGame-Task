package com.saulius.pokergame.entities;

import java.util.*;

public class CardDeck {

    private final TreeSet<Card> deckOfCards;
    private final int maxDeckSize;

    public CardDeck(int maxDeckSize) {
        this.deckOfCards = new TreeSet<>();
        this.maxDeckSize = maxDeckSize;
    }

    public TreeSet<Card> getDeckOfCards() {
        return deckOfCards;
    }

    /*
    In this task checking whether it's possible to addCard is redundant (because it's checked when adding cards),
    so it's here only for sanity check and good code practice.
    */
    public void addCard(Card card) {
        if (canAddCard()) {
            deckOfCards.add(card);
        }
    }

    public boolean canAddCard () {
        return deckOfCards.size() < maxDeckSize;
    }

    //This is only used in tests
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
