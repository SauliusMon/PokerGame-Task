package com.saulius.pokergame;

import com.saulius.pokergame.entities.Card;
import com.saulius.pokergame.entities.CardDeck;
import com.saulius.pokergame.enums.CardSuit;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

public class CardDeckTest {

    private final Card testingCard0 = new Card(5, CardSuit.D);
    private final Card testingCard1 = new Card(12, CardSuit.S);
    private final Card testingCard2 = new Card(7, CardSuit.D);
    private final Card testingCard3 = new Card(6, CardSuit.C);

    @Test
    public void cardDeck_addWithSize2() {
        CardDeck cardDeck0 = new CardDeck(2);
        cardDeck0.addCard(testingCard0);
        cardDeck0.addCard(testingCard1);

        Assertions.assertEquals(2, cardDeck0.getDeckOfCards().size(), "CardDeck size is invalid.");
        Assertions.assertEquals(testingCard0, cardDeck0.getDeckOfCards().first(), "CardDeck has wrong card in it.");
        Assertions.assertEquals(testingCard1, cardDeck0.getDeckOfCards().last(), "CardDeck has wrong card in it.");
    }

    @Test
    public void cardDeck_addWithSize4() {
        CardDeck cardDeck1 = new CardDeck(4);
        cardDeck1.addCard(testingCard2);
        cardDeck1.addCard(testingCard3);
        cardDeck1.addCard(testingCard0);
        cardDeck1.addCard(testingCard1);

        Assertions.assertEquals(4, cardDeck1.getDeckOfCards().size(), "CardDeck size is invalid.");
        Iterator<Card> iterator = cardDeck1.getDeckOfCards().iterator();
        Assertions.assertEquals(testingCard0, iterator.next(),"CardDeck has wrong card in it.");
        Assertions.assertEquals(testingCard3, iterator.next(),"CardDeck has wrong card in it.");
        Assertions.assertEquals(testingCard2, iterator.next(),"CardDeck has wrong card in it.");
        Assertions.assertEquals(testingCard1, iterator.next(),"CardDeck has wrong card in it.");
    }

    @Test
    public void cardDeck_sizeCantExceedLimit() {
        CardDeck cardDeck2 = new CardDeck(4);
        cardDeck2.addCards(testingCard0, testingCard1, testingCard2, testingCard3);
        Assertions.assertEquals(4, cardDeck2.getDeckOfCards().size(), "CardDeck addCards() method is not working correctly.");

        cardDeck2.addCard(new Card(1, CardSuit.C));
        Assertions.assertEquals(4, cardDeck2.getDeckOfCards().size(), "CardDeck shouldn't add card if it reached maximum size.");

        cardDeck2.addCards(new Card(3, CardSuit.D));
        Assertions.assertEquals(4, cardDeck2.getDeckOfCards().size(), "CardDeck shouldn't add card if it reached maximum size.");
    }
}
