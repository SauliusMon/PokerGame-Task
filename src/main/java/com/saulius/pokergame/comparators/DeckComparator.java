package com.saulius.pokergame.comparators;

import com.saulius.pokergame.entities.Card;
import com.saulius.pokergame.entities.PlayerCardDeck;
import com.saulius.pokergame.enums.CardSuit;

import java.util.Comparator;

public class DeckComparator {

    //TODO class
    public static String compareDecks (PlayerCardDeck cardDeckOfPlayer1, PlayerCardDeck cardDeckOfPlayer2) {

        return "Player 1";
    }

    class CardsComparator implements Comparator<Card> {
        @Override
        public int compare(Card card1, Card card2) {
            return 0;
        }
    }
}
