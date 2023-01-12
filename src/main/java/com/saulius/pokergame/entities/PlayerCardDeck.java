package com.saulius.pokergame.entities;

public class PlayerCardDeck extends CardDeck{

    private final String playerName;

    public PlayerCardDeck(String playerName, int maxDeckSize) {
        super(maxDeckSize);
        this.playerName = playerName;
    }

    public String getPlayerName() {
        return this.playerName;
    }

    @Override
    public String toString() {
        return "PlayerCardDeck{" +
                "playerName='" + playerName +
                super.toString() + '}';
    }
}
