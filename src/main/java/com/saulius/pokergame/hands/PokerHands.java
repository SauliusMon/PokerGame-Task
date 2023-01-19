package com.saulius.pokergame.hands;

import com.saulius.pokergame.entities.Card;
import com.saulius.pokergame.entities.PlayerCardDeck;
import com.saulius.pokergame.enums.CardSuit;

import java.util.*;

public class PokerHands {

    public static RankedPlayerHand lookForHandInCardDeck (PlayerCardDeck playerCardDeck) {
        boolean areCardsOfTheSameSuit = true;
        boolean areCardsOfConsecutiveValues = false;

        TreeSet<Card> playerCardDeckTreeSet = playerCardDeck.getDeckOfCards();
        TreeSet<Card> rankedCards = new TreeSet<>();
        TreeSet<Card> unrankedCards = new TreeSet<>();

        /*
        Finds every single pair, three of a kind, four of a kind and whether cards are of the same suit.
        For performance’s sake it happens at the same time: this way CardDeck needs to be iterated through only once.

        Because of sorting, if there is a pair of 10 and a pair of 11, 10s will be neighbors and go before 11.

        If first card and second card values are even, they are put into HashMap, or the amount of appearance times for that specific value is increased by one.
        */
        HashMap<Integer, Integer> cardValueWithAmountOfAppearances = new HashMap<>();

        /*
        Gets first card before loop starts and skips first iteration of that loop
         */
        Iterator<Card> playerCardDeckIterator = playerCardDeckTreeSet.iterator();
        //Skipping first iteration
        Card previousCard = playerCardDeckIterator.next();
        CardSuit cardSuit = previousCard.getCardSuit();

        //This is needed so that the first card (previousCard) would be added into ranked or unranked cards TreeSet
        boolean previousCardRanked = false;

        while (playerCardDeckIterator.hasNext()) {
            Card currentCard = playerCardDeckIterator.next();
            if (currentCard.getCardValue() == previousCard.getCardValue()) {
                int valueOfPair = currentCard.getCardValue();
                if (cardValueWithAmountOfAppearances.containsKey(valueOfPair)) {
                    cardValueWithAmountOfAppearances.replace(valueOfPair, cardValueWithAmountOfAppearances.get(valueOfPair) + 1);
                } else {
                    cardValueWithAmountOfAppearances.put(valueOfPair, 2);
                }
                /*
                Adding both matching cards to a ranked set
                In a 3 of a kind or 4 of a kind cases previous card wouldn't be added because of a TreeSet characteristics
                */
                rankedCards.add(currentCard);
                rankedCards.add(previousCard);
                previousCardRanked = true;
            }
            else {
                //In a case where this card is the last one, adding it to an unranked set
                if (!playerCardDeckIterator.hasNext()) {
                    unrankedCards.add(currentCard);
                }
                //If a previousCard was unranked, it should add it to an unranked set
                if (!previousCardRanked) {
                    unrankedCards.add(previousCard);
                }
                previousCardRanked = false;
            }
            //Checking if hand has the same suits. If cardSuits are not even once, it means suits differ in hand
            if (cardSuit != currentCard.getCardSuit()) {
                areCardsOfTheSameSuit = false;
            }
            previousCard = currentCard;
        }
        
        /*
        Checks if it's a Royal Flush
        Returns the whole deck to rankedCards instead of rankedCards because all 5 cards are used in combination
        and rankedCards are used for pair combinations
        The same thing happens later on with 5 card combos, such as Flush, Straight and Full House.
        */
        int smallestCardValue = playerCardDeckTreeSet.first().getCardValue();
        int highestCardValue = playerCardDeckTreeSet.last().getCardValue();
        if (smallestCardValue == 10 && highestCardValue == 14 && areCardsOfTheSameSuit) {
            return new RankedPlayerHand(10, playerCardDeck.getDeckOfCards(), null);
        }

        /*
        Checks whether all cards have consecutive values
        RankedCards.size() is needed to check whether program found any pairs
        */
        if (rankedCards.size() == 0) {
            areCardsOfConsecutiveValues = smallestCardValue + playerCardDeckTreeSet.size() - 1 == highestCardValue;
        }

        /*
        Checks if it's a Straight Flush
        */
        if (areCardsOfTheSameSuit && areCardsOfConsecutiveValues) {
            return new RankedPlayerHand(9, playerCardDeck.getDeckOfCards(), null);
        }

        /*
        Checks if it's a Four of a Kind
        Same value reappears 4 times in a CardDeck
        In this case there is no point to know the value of last card.
        Reason being, four of a kind can't be of the same value for both players,
        so comparing should never start with unrankedCard set.
         */
        if (cardValueWithAmountOfAppearances.containsValue(4)) {
            return new RankedPlayerHand(8, rankedCards, null);
        }

        /*
        Checks if it's a full house.
        It's a full house if it found 2 values reappearing (size = 2) and one of those has appeared 3 times (containsValue(3))
        In a case its only 2 reappearing values with a size of 2, it's Two pairs.
         */
        if (cardValueWithAmountOfAppearances.size() == 2 && cardValueWithAmountOfAppearances.containsValue(3)) {
            return new RankedPlayerHand(7, playerCardDeck.getDeckOfCards(), null);

        }
        /*
        Checks if it's a Flush
        */
        if (areCardsOfTheSameSuit) {
            return new RankedPlayerHand(6, playerCardDeck.getDeckOfCards(), null);

        }
        /*
        Checks if it's a Straight
        */
        if (areCardsOfConsecutiveValues) {
            return new RankedPlayerHand(5, playerCardDeck.getDeckOfCards(), null);

        }
        /*
        Checks if it's a Three of a Kind
        For Three of a kind to have same value in both player hands, there should be 6 of the same value unique cards, when currently there is 4,
        so comparator should never start comparing unrankedCards set.
        */
        if (cardValueWithAmountOfAppearances.containsValue(3)) {
            return new RankedPlayerHand(4, rankedCards, null);

        }
        /*
        Checks if it's Two Pairs
        */
        if (cardValueWithAmountOfAppearances.size() == 2) {
            return new RankedPlayerHand(3, rankedCards, unrankedCards);
        }
        /*
        Checks if it's One Pair
        */
        if (cardValueWithAmountOfAppearances.size() != 0) {
            return new RankedPlayerHand(2, rankedCards, unrankedCards);

        }
        /*
        Returns 1 if it's a High Card
        If it's a High Card, it means all Cards are unranked.
        */
        return new RankedPlayerHand(1, null, playerCardDeck.getDeckOfCards());
    }
}
