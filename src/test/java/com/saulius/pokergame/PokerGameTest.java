package com.saulius.pokergame;

import com.saulius.pokergame.entities.Card;
import com.saulius.pokergame.entities.CardDeck;
import com.saulius.pokergame.entities.PlayerCardDeck;
import com.saulius.pokergame.entities.RankedPlayerCardDeck;
import com.saulius.pokergame.enums.CardSuit;
import com.saulius.pokergame.enums.CardValue;
import com.saulius.pokergame.games.PlayPoker;
import com.saulius.pokergame.hands.PokerHands;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PokerGameTest {

    @Test
    public void testingEnumsValuesLength() {
        Assertions.assertEquals(5, CardValue.values().length, "CardValue has wrong amount of Enums: " + CardValue.values().length + " amount.");
        Assertions.assertEquals(4, CardSuit.values().length, "CardSuit has wrong amount of Enums: " + CardSuit.values().length + " amount.");
    }

    @Test
    public void testingCardClass() {
        Card testingCard0 = new Card(10, CardSuit.C);
        Assertions.assertEquals(CardSuit.C, testingCard0.getCardSuit(),"Card class has set wrong suit.");
        Assertions.assertEquals(10, testingCard0.getCardValue(),"Card class has set wrong value.");
        Assertions.assertEquals("10C", testingCard0.toString(), "Card class toString() method returns an invalid string.");

        Card testingCard1 = new Card(12, CardSuit.H);
        Assertions.assertEquals(CardSuit.H, testingCard1.getCardSuit(),"Card class has set wrong suit.");
        Assertions.assertEquals(12, testingCard1.getCardValue(),"Card class has set wrong value.");
        Assertions.assertEquals("12H", testingCard1.toString(), "Card class toString() method returns an invalid string.");
    }

    @Test
    public void testingCardDeckClass() {
        Card testingCard0 = new Card(5, CardSuit.D);
        Card testingCard1 = new Card(12, CardSuit.S);

        CardDeck cardDeck0 = new CardDeck(2);
        cardDeck0.addCard(testingCard0);
        cardDeck0.addCard(testingCard1);

        Assertions.assertEquals(2, cardDeck0.getDeckOfCards().size(), "CardDeck size is invalid.");
        Assertions.assertEquals(testingCard0, cardDeck0.getDeckOfCards().get(0), "CardDeck has wrong card in it.");
        Assertions.assertEquals(testingCard1, cardDeck0.getDeckOfCards().get(1), "CardDeck has wrong card in it.");

        Card testingCard2 = new Card(7, CardSuit.D);
        Card testingCard3 = new Card(6, CardSuit.C);

        CardDeck cardDeck1 = new CardDeck(4);
        cardDeck1.addCard(testingCard2);
        cardDeck1.addCard(testingCard3);
        cardDeck1.addCard(testingCard0);
        cardDeck1.addCard(testingCard1);

        Assertions.assertEquals(4, cardDeck1.getDeckOfCards().size(), "CardDeck size is invalid.");
        Assertions.assertEquals(testingCard2, cardDeck1.getDeckOfCards().get(0),"CardDeck has wrong card in it.");
        Assertions.assertEquals(testingCard3, cardDeck1.getDeckOfCards().get(1),"CardDeck has wrong card in it.");
        Assertions.assertEquals(testingCard0, cardDeck1.getDeckOfCards().get(2),"CardDeck has wrong card in it.");
        Assertions.assertEquals(testingCard1, cardDeck1.getDeckOfCards().get(3),"CardDeck has wrong card in it.");

        CardDeck cardDeck2 = new CardDeck(4);
        cardDeck2.addCards(testingCard0, testingCard1, testingCard2, testingCard3);
        Assertions.assertEquals(4, cardDeck2.getDeckOfCards().size(), "CardDeck addCards() method is not working correctly.");

        cardDeck2.addCard(new Card(1, CardSuit.C));
        Assertions.assertEquals(4, cardDeck2.getDeckOfCards().size(), "CardDeck shouldn't add card if it reached maximum size.");

        cardDeck2.addCards(new Card(3, CardSuit.D));
        Assertions.assertEquals(4, cardDeck2.getDeckOfCards().size(), "CardDeck shouldn't add card if it reached maximum size.");
    }

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


    private final String royalFlushToCompare = "TH JH QH KH AH AD JD TD KD QD";
    private final String straightFlushToCompare = "8C QC JC 9C TC 3S 5S 2S 4S 6S";


    @Test
    public void testingPokerHandsComparator() {
        List<PlayerCardDeck> playerCardDecks = new PlayPoker().stringToCardDecks(royalFlushToCompare, 5);
        RankedPlayerCardDeck firstPlayerRankedDeck = PokerHands.lookForHandInCardDeck(playerCardDecks.get(0));
        RankedPlayerCardDeck secondPlayerRankedDeck = PokerHands.lookForHandInCardDeck(playerCardDecks.get(0));
        Assertions.assertEquals(0, firstPlayerRankedDeck.compareTo(secondPlayerRankedDeck));


    }
}
