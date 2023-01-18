package com.saulius.pokergame.entities;

import java.util.*;

public class PlayerCardDeck extends CardDeck {

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

    //Converts line of cards to a CardDeck
    public static List<PlayerCardDeck> stringToCardDecks (String cardsString, int amountOfCardsInARow, int amountOfPlayers) {

        List<PlayerCardDeck> playersCardDecksList = new ArrayList<>(amountOfPlayers);
        Queue<PlayerCardDeck> playersCardDeckQueue = new ArrayDeque<>(amountOfPlayers);

        String[] individualCards = cardsString.split(" ", amountOfCardsInARow);
        int playerDeckSize = amountOfCardsInARow / amountOfPlayers;

        for (int x = 1; x <= amountOfPlayers; x++) {
            playersCardDeckQueue.add(new PlayerCardDeck("Player " + x, playerDeckSize));
        }
        PlayerCardDeck cardDeckOfPlayer = playersCardDeckQueue.remove();

        for (String cardString : individualCards) {
            Card card = Card.charToCardConverter(cardString.charAt(0), cardString.charAt(1));

            if (cardDeckOfPlayer.canAddCard()) {
                cardDeckOfPlayer.addCard(card);
            } else {
                playersCardDecksList.add(cardDeckOfPlayer);
                cardDeckOfPlayer = playersCardDeckQueue.remove();
                //In case maximum CardDeck size is 0
                if (cardDeckOfPlayer.canAddCard()) {
                    cardDeckOfPlayer.addCard(card);
                }
            }
        }
        playersCardDecksList.add(cardDeckOfPlayer);

        return playersCardDecksList;
    }
}
