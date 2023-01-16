package com.saulius.pokergame;

import com.saulius.pokergame.entities.Card;
import com.saulius.pokergame.entities.CardDeck;
import com.saulius.pokergame.entities.PlayerCardDeck;
import com.saulius.pokergame.enums.CardSuit;
import com.saulius.pokergame.enums.CardValue;
import com.saulius.pokergame.games.PlayPoker;
import com.saulius.pokergame.hands.PokerHands;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
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
        Assertions.assertEquals(testingCard0, cardDeck0.getDeckOfCards().first(), "CardDeck has wrong card in it.");
        Assertions.assertEquals(testingCard1, cardDeck0.getDeckOfCards().last(), "CardDeck has wrong card in it.");

        Card testingCard2 = new Card(7, CardSuit.D);
        Card testingCard3 = new Card(6, CardSuit.C);

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

    @Test
    public void testingPokerHandsComparator() {
        String royalFlushToCompare = "TH JH QH KH AH AD JD TD KD QD";
        List<PlayerCardDeck> royalFlushCardDecks = new PlayPoker().stringToCardDecks(royalFlushToCompare, 10, 2);
        Assertions.assertEquals(0, PokerHands.lookForHandInCardDeck(royalFlushCardDecks.get(0)).compareTo(
                PokerHands.lookForHandInCardDeck(royalFlushCardDecks.get(0))), "Comparator is wrong at Royal Flush hand.");

        String straightFlushToCompare = "8C QC JC 9C TC 3S 5S 2S 4S 6S";
        List<PlayerCardDeck> straightFlushCardDecks = new PlayPoker().stringToCardDecks(straightFlushToCompare, 10, 2);
        Assertions.assertEquals(1, PokerHands.lookForHandInCardDeck(straightFlushCardDecks.get(0)).compareTo(
                PokerHands.lookForHandInCardDeck(straightFlushCardDecks.get(1))), "Comparator is wrong at Straight Flush hand.");

        String fourOfAKindToCompare = "AC AH 2C AS AD 4S 4H 4C 4D JS";
        List<PlayerCardDeck> fourOfAKindCardDecks = new PlayPoker().stringToCardDecks(fourOfAKindToCompare, 10, 2);
        Assertions.assertEquals(1, PokerHands.lookForHandInCardDeck(fourOfAKindCardDecks.get(0)).compareTo(
                PokerHands.lookForHandInCardDeck(fourOfAKindCardDecks.get(1))), "Comparator is wrong at Four of a Kind hand.");

        String fullHouseToCompare = "7D 7C AH AD AC TD JH JD TC JS";
        List<PlayerCardDeck> fullHouseCardDecks = new PlayPoker().stringToCardDecks(fullHouseToCompare, 10, 2);
        Assertions.assertEquals(1, PokerHands.lookForHandInCardDeck(fullHouseCardDecks.get(0)).compareTo(
                PokerHands.lookForHandInCardDeck(fullHouseCardDecks.get(1))), "Comparator is wrong at Full house hand.");

        String flushToCompare = "AD 5D TD KD QD TC 5C 3C 6C KC";
        List<PlayerCardDeck> flushCardDecks = new PlayPoker().stringToCardDecks(flushToCompare, 10, 2);
        Assertions.assertEquals(1, PokerHands.lookForHandInCardDeck(flushCardDecks.get(0)).compareTo(
                PokerHands.lookForHandInCardDeck(flushCardDecks.get(1))), "Comparator is wrong at Flush hand");

        String straightToCompare = "3S 5D 2S 4S 6S TC JD QH KC AC";
        List<PlayerCardDeck> straightCardDecks = new PlayPoker().stringToCardDecks(straightToCompare, 10, 2);
        Assertions.assertEquals(-1, PokerHands.lookForHandInCardDeck(straightCardDecks.get(0)).compareTo(
                PokerHands.lookForHandInCardDeck(straightCardDecks.get(1))), "Comparator is wrong at Straight hand");

        String threeOfAKindToCompare = "4C 2D 4D 4H 3C 7D JD JC JH 6D";
        List<PlayerCardDeck> threeOfAKindCardDecks = new PlayPoker().stringToCardDecks(threeOfAKindToCompare, 10, 2);
        Assertions.assertEquals(-1, PokerHands.lookForHandInCardDeck(threeOfAKindCardDecks.get(0)).compareTo(
                PokerHands.lookForHandInCardDeck(threeOfAKindCardDecks.get(1))), "Comparator is wrong at Three of a Kind hand");

        String twoPairsToCompare = "6S 7S JD JS 6C AS AC KH 2S 2D";
        List<PlayerCardDeck> twoPairsCardDecks = new PlayPoker().stringToCardDecks(twoPairsToCompare, 10, 2);
        Assertions.assertEquals(-1, PokerHands.lookForHandInCardDeck(twoPairsCardDecks.get(0)).compareTo(
                PokerHands.lookForHandInCardDeck(twoPairsCardDecks.get(1))), "Comparator is wrong at Two Pairs hand");

        String onePairToCompare = "QD QH 5D 4D 2C 6H 2D KH 7C KS";
        List<PlayerCardDeck> onePairCardDecks = new PlayPoker().stringToCardDecks(onePairToCompare, 10, 2);
        Assertions.assertEquals(-1, PokerHands.lookForHandInCardDeck(onePairCardDecks.get(0)).compareTo(
                PokerHands.lookForHandInCardDeck(onePairCardDecks.get(1))), "Comparator is wrong at One Pair hand");

        String highCardToCompare = "4H 3C 7H 6C JS KC AS TD 5H 6H";
        List<PlayerCardDeck> highCardCardDecks = new PlayPoker().stringToCardDecks(highCardToCompare, 10, 2);
        Assertions.assertEquals(-1, PokerHands.lookForHandInCardDeck(highCardCardDecks.get(0)).compareTo(
                PokerHands.lookForHandInCardDeck(highCardCardDecks.get(1))), "Comparator is wrong at High Card hand");
    }
}
