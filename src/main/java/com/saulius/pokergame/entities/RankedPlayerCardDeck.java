package com.saulius.pokergame.entities;

import java.util.List;

public class RankedPlayerCardDeck {

    private int deckRanking;
    private List<CardDeck> rankedCardsList;
    private List<CardDeck> unrankedCardsList;

    private PlayerCardDeck playerCardDeck;

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
}
