package com.saulius.pokergame;

import com.saulius.pokergame.Entity.Card;
import com.saulius.pokergame.Entity.CardDeck;
import com.saulius.pokergame.enums.CardSuit;
import com.saulius.pokergame.enums.CardValue;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;

public class PokerGameTest {

    @Test
    public void testingEnumsValuesLength() {
        Assertions.assertEquals(5, CardValue.values().length, "CardValue has wrong amount of Enums: " + CardValue.values().length + " amount.");
        Assertions.assertEquals(4, CardSuit.values().length, "CardSuit has wrong amount of Enums: " + CardSuit.values().length + " amount.");
    }

    @Test
    public void testingCardClass() {
        Card testingCard0 = new Card(10, CardSuit.C);
        Assertions.assertEquals(testingCard0.getCardSuit(), CardSuit.C, "Card class has set wrong suit.");
        Assertions.assertEquals(testingCard0.getCardValue(), 10, "Card class has set wrong value.");
        Assertions.assertEquals(testingCard0.toString(), "10C", "Card class toString() method returns an invalid string.");

        Card testingCard1 = new Card(12, CardSuit.H);
        Assertions.assertEquals(testingCard1.getCardSuit(), CardSuit.H, "Card class has set wrong suit.");
        Assertions.assertEquals(testingCard1.getCardValue(), 12, "Card class has set wrong value.");
        Assertions.assertEquals(testingCard1.toString(), "12H", "Card class toString() method returns an invalid string.");
    }

    @Test
    public void testingCardDeckClass() {
        Card testingCard0 = new Card(5, CardSuit.D);
        Card testingCard1 = new Card(12, CardSuit.S);

        CardDeck cardDeck0 = new CardDeck();
        cardDeck0.addCard(testingCard0);
        cardDeck0.addCards(testingCard1);

        Assertions.assertEquals(cardDeck0.getDeckOfCards().size(), 2, "CardDeck size is invalid.");
        Assertions.assertEquals(cardDeck0.getDeckOfCards().get(0), testingCard0, "CardDeck has wrong card in it.");
        Assertions.assertEquals(cardDeck0.getDeckOfCards().get(1), testingCard1, "CardDeck has wrong card in it.");

        Card testingCard2 = new Card(7, CardSuit.D);
        Card testingCard3 = new Card(6, CardSuit.C);

        CardDeck cardDeck1 = new CardDeck();
        cardDeck1.addCards(testingCard2);
        cardDeck1.addCards(testingCard3);
        cardDeck1.addCard(testingCard0);
        cardDeck1.addCards(testingCard1);

        Assertions.assertEquals(cardDeck1.getDeckOfCards().size(), 4, "CardDeck size is invalid.");
        Assertions.assertEquals(cardDeck1.getDeckOfCards().get(0), testingCard2, "CardDeck has wrong card in it.");
        Assertions.assertEquals(cardDeck1.getDeckOfCards().get(1), testingCard3, "CardDeck has wrong card in it.");
        Assertions.assertEquals(cardDeck1.getDeckOfCards().get(2), testingCard0, "CardDeck has wrong card in it.");
        Assertions.assertEquals(cardDeck1.getDeckOfCards().get(3), testingCard1, "CardDeck has wrong card in it.");
    }
}
