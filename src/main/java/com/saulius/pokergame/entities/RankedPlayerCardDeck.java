package com.saulius.pokergame.entities;

import java.util.List;
import java.util.TreeSet;

@SuppressWarnings("rawtypes")
public class RankedPlayerCardDeck <T extends RankedPlayerCardDeck> implements Comparable<T>{

    private final int deckRanking;
    private final TreeSet<Card> rankedCardsList;
    private final TreeSet<Card> unrankedCardsList;
    private final PlayerCardDeck playerCardDeck;

    public RankedPlayerCardDeck(int deckRanking, TreeSet<Card> rankedCardsList, TreeSet<Card> unrankedCardsList, PlayerCardDeck playerCardDeck) {
        this.deckRanking = deckRanking;
        this.rankedCardsList = rankedCardsList;
        this.unrankedCardsList = unrankedCardsList;
        this.playerCardDeck = playerCardDeck;
    }

    public int getDeckRanking() {
        return deckRanking;
    }

    public TreeSet<Card> getRankedCardsList() {
        return rankedCardsList;
    }

    public TreeSet<Card> getUnrankedCardsList() {
        return unrankedCardsList;
    }

    public PlayerCardDeck getPlayerCardDeck() {
        return playerCardDeck;
    }


    @Override
    public int compareTo(T cardDeckObject) {
        return this.getDeckRanking() - cardDeckObject.getDeckRanking();
    }
}
