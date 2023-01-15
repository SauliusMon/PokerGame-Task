package com.saulius.pokergame.entities;

import java.util.List;
import java.util.TreeSet;

@SuppressWarnings("rawtypes")
public class RankedPlayerCardDeck <T extends RankedPlayerCardDeck> implements Comparable<T>{

    private final int deckRanking;
    private final TreeSet<CardDeck> rankedCardsList;
    private final TreeSet<CardDeck> unrankedCardsList;
    private final PlayerCardDeck playerCardDeck;

    public RankedPlayerCardDeck(int deckRanking, TreeSet<CardDeck> rankedCardsList, TreeSet<CardDeck> unrankedCardsList, PlayerCardDeck playerCardDeck) {
        this.deckRanking = deckRanking;
        this.rankedCardsList = rankedCardsList;
        this.unrankedCardsList = unrankedCardsList;
        this.playerCardDeck = playerCardDeck;
    }

    public int getDeckRanking() {
        return deckRanking;
    }

    public TreeSet<CardDeck> getRankedCardsList() {
        return rankedCardsList;
    }

    public TreeSet<CardDeck> getUnrankedCardsList() {
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
