package com.saulius.pokergame;

import com.saulius.pokergame.entities.PlayerCardDeck;
import com.saulius.pokergame.hands.PokerHands;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class PokerHandsComparatorTest {

    @Test
    public void testingPokerHandsComparator_royalFlush() {
        String royalFlushToCompare = "TH JH QH KH AH AD JD TD KD QD";
        List<PlayerCardDeck> royalFlushCardDecks = PlayerCardDeck.stringToCardDecks(royalFlushToCompare, 10, 2);
        Assertions.assertEquals(0, PokerHands.lookForHandInCardDeck(royalFlushCardDecks.get(0)).compareTo(
                PokerHands.lookForHandInCardDeck(royalFlushCardDecks.get(0))), "Comparator is wrong at Royal Flush hand.");
    }

    @Test
    public void testingPokerHandsComparator_straightFlush() {
        String straightFlushToCompare = "8C QC JC 9C TC 3S 5S 2S 4S 6S";
        List<PlayerCardDeck> straightFlushCardDecks = PlayerCardDeck.stringToCardDecks(straightFlushToCompare, 10, 2);
        Assertions.assertEquals(1, PokerHands.lookForHandInCardDeck(straightFlushCardDecks.get(0)).compareTo(
                PokerHands.lookForHandInCardDeck(straightFlushCardDecks.get(1))), "Comparator is wrong at Straight Flush hand.");
    }


    @Test
    public void testingPokerHandsComparator_fourOfAKind() {
        String fourOfAKindToCompare = "AC AH 2C AS AD 4S 4H 4C 4D JS";
        List<PlayerCardDeck> fourOfAKindCardDecks = PlayerCardDeck.stringToCardDecks(fourOfAKindToCompare, 10, 2);
        Assertions.assertEquals(1, PokerHands.lookForHandInCardDeck(fourOfAKindCardDecks.get(0)).compareTo(
                PokerHands.lookForHandInCardDeck(fourOfAKindCardDecks.get(1))), "Comparator is wrong at Four of a Kind hand.");
    }

    @Test
    public void testingPokerHandsComparator_fullHouse() {
        String fullHouseToCompare = "7D 7C AH AD AC TD JH JD TC JS";
        List<PlayerCardDeck> fullHouseCardDecks = PlayerCardDeck.stringToCardDecks(fullHouseToCompare, 10, 2);
        Assertions.assertEquals(1, PokerHands.lookForHandInCardDeck(fullHouseCardDecks.get(0)).compareTo(
                PokerHands.lookForHandInCardDeck(fullHouseCardDecks.get(1))), "Comparator is wrong at Full house hand.");
    }


    @Test
    public void testingPokerHandsComparator_flush() {
        String flushToCompare = "AD 5D TD KD QD TC 5C 3C 6C KC";
        List<PlayerCardDeck> flushCardDecks = PlayerCardDeck.stringToCardDecks(flushToCompare, 10, 2);
        Assertions.assertEquals(1, PokerHands.lookForHandInCardDeck(flushCardDecks.get(0)).compareTo(
                PokerHands.lookForHandInCardDeck(flushCardDecks.get(1))), "Comparator is wrong at Flush hand");
    }

    @Test
    public void testingPokerHandsComparator_straight() {
        String straightToCompare = "3S 5D 2S 4S 6S TC JD QH KC AC";
        List<PlayerCardDeck> straightCardDecks = PlayerCardDeck.stringToCardDecks(straightToCompare, 10, 2);
        Assertions.assertEquals(-1, PokerHands.lookForHandInCardDeck(straightCardDecks.get(0)).compareTo(
                PokerHands.lookForHandInCardDeck(straightCardDecks.get(1))), "Comparator is wrong at Straight hand");
    }

    @Test
    public void testingPokerHandsComparator_threeOfAKind() {
        String threeOfAKindToCompare = "4C 2D 4D 4H 3C 7D JD JC JH 6D";
        List<PlayerCardDeck> threeOfAKindCardDecks = PlayerCardDeck.stringToCardDecks(threeOfAKindToCompare, 10, 2);
        Assertions.assertEquals(-1, PokerHands.lookForHandInCardDeck(threeOfAKindCardDecks.get(0)).compareTo(
                PokerHands.lookForHandInCardDeck(threeOfAKindCardDecks.get(1))), "Comparator is wrong at Three of a Kind hand");
    }

    @Test
    public void testingPokerHandsComparator_twoPairs() {
        String twoPairsToCompare = "6S 7S JD JS 6C AS AC KH 2S 2D";
        List<PlayerCardDeck> twoPairsCardDecks = PlayerCardDeck.stringToCardDecks(twoPairsToCompare, 10, 2);
        Assertions.assertEquals(-1, PokerHands.lookForHandInCardDeck(twoPairsCardDecks.get(0)).compareTo(
                PokerHands.lookForHandInCardDeck(twoPairsCardDecks.get(1))), "Comparator is wrong at Two Pairs hand");
    }

    @Test
    public void testingPokerHandsComparator_onePair() {
        String onePairToCompare = "QD QH 5D 4D 2C 6H 2D KH 7C KS";
        List<PlayerCardDeck> onePairCardDecks = PlayerCardDeck.stringToCardDecks(onePairToCompare, 10, 2);
        Assertions.assertEquals(-1, PokerHands.lookForHandInCardDeck(onePairCardDecks.get(0)).compareTo(
                PokerHands.lookForHandInCardDeck(onePairCardDecks.get(1))), "Comparator is wrong at One Pair hand");
    }

    @Test
    public void testingPokerHandsComparator_highCard() {
        String highCardToCompare = "4H 3C 7H 6C JS KC AS TD 5H 6H";
        List<PlayerCardDeck> highCardCardDecks = PlayerCardDeck.stringToCardDecks(highCardToCompare, 10, 2);
        Assertions.assertEquals(-1, PokerHands.lookForHandInCardDeck(highCardCardDecks.get(0)).compareTo(
                PokerHands.lookForHandInCardDeck(highCardCardDecks.get(1))), "Comparator is wrong at High Card hand");
    }
}
