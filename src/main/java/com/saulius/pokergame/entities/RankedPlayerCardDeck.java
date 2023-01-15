package com.saulius.pokergame.entities;

import java.util.List;

@SuppressWarnings("rawtypes")
public class RankedPlayerCardDeck <T extends RankedPlayerCardDeck> implements Comparable<T>{

    private final int deckRanking;
    private final List<CardDeck> rankedCardsList;
    private final List<CardDeck> unrankedCardsList;
    private final PlayerCardDeck playerCardDeck;

    public RankedPlayerCardDeck(int deckRanking, List<CardDeck> rankedCardsList, List<CardDeck> unrankedCardsList, PlayerCardDeck playerCardDeck) {
        this.deckRanking = deckRanking;
        this.rankedCardsList = rankedCardsList;
        this.unrankedCardsList = unrankedCardsList;
        this.playerCardDeck = playerCardDeck;
    }

    public int getDeckRanking() {
        return deckRanking;
    }

    public List<CardDeck> getRankedCardsList() {
        return rankedCardsList;
    }

    public List<CardDeck> getUnrankedCardsList() {
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
