package com.saulius.pokergame;

import com.saulius.pokergame.entities.Card;
import com.saulius.pokergame.entities.PlayerCardDeck;
import com.saulius.pokergame.enums.CardSuit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PlayerCardDeckTest {

    @Test
    public void testingPlayerCardDeckClass() {
        Card testingCard0 = new Card(4, CardSuit.H);
        Card testingCard1 = new Card(2, CardSuit.S);
        Card testingCard2 = new Card(2, CardSuit.S);

        PlayerCardDeck playerCardDeck = new PlayerCardDeck("Player Deck", 2);
        playerCardDeck.addCard(testingCard0);
        playerCardDeck.addCards(testingCard1);
        Assertions.assertEquals(2, playerCardDeck.getDeckOfCards().size(), "PlayerCardDeck size is invalid.");

        playerCardDeck.addCards(testingCard2);
        Assertions.assertEquals(2, playerCardDeck.getDeckOfCards().size(), "PlayerCardDeck shouldn't add card if it reached maximum size.");
        Assertions.assertEquals("Player Deck", playerCardDeck.getPlayerName(),"PlayerCardDeck names don't match.");
    }
}
