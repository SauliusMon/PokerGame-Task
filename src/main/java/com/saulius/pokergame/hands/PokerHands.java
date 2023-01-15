package com.saulius.pokergame.hands;

import com.saulius.pokergame.entities.Card;
import com.saulius.pokergame.entities.PlayerCardDeck;
import com.saulius.pokergame.entities.RankedPlayerCardDeck;
import com.saulius.pokergame.enums.CardSuit;

import java.util.*;

public class PokerHands {

    @SuppressWarnings("rawtypes")
    public static RankedPlayerCardDeck lookForHandInCardDeck (PlayerCardDeck playerCardDeck) {
        boolean areCardsOfTheSameSuit = true;
        boolean areCardsOfConsecutiveValues;

        List<Card> cardsList = playerCardDeck.getDeckOfCards();

        //Sorts cards by ascending values
        cardsList.sort(Comparator.comparingInt(Card::getCardValue));

        /*
        Checks whether all cards are of the same suit
        */
        CardSuit cardSuit = cardsList.get(0).getCardSuit();
        HashMap<Integer, Integer> cardValueWithAmountOfAppearances = new HashMap<>();

        /*
        Finds every single pair, three of a kind, four of a kind and whether cards are of the same suit.
        For performance’s sake it happens at the same time: this way CardDeck needs to be iterated through only once.
        Because of a low chance for CardDeck to have the highest tier hand, making those calculations earlier shouldn't diminish performance.

        Because the List is sorted in ascending order, in a case there is a pair in CardDeck, the latter should be neighbors.
        E.g, if there is a pair of 10 and 11, 10s will be neighbors and go before 11.

        Loop iterates until second to last member, and checks whether first and second values are even.
        If they are, they are put into HashMap, or the amount of appearance times in CardDeck increases by one.
        */
        for (int i = 0; i < cardsList.size() - 1; i++) {
                Card card = cardsList.get(i);
            if (card.getCardValue() == cardsList.get(i + 1).getCardValue()) {
                int valueOfPair = card.getCardValue();
                if (cardValueWithAmountOfAppearances.containsKey(valueOfPair)) {
                    cardValueWithAmountOfAppearances.replace(valueOfPair, cardValueWithAmountOfAppearances.get(valueOfPair) + 1);
                }
                else {
                    cardValueWithAmountOfAppearances.put(card.getCardValue(), 2);
                }
            }

            if (cardSuit != card.getCardSuit()) {
                areCardsOfTheSameSuit = false;
            }
        }

        if (areCardsOfTheSameSuit) {
            areCardsOfTheSameSuit = cardSuit == cardsList.get(cardsList.size() - 1).getCardSuit();
        }
        
        /*
        Checks if it's a Royal Flush
        */
        int smallestCardValue = cardsList.get(0).getCardValue();
        int highestCardValue = cardsList.get(cardsList.size() - 1).getCardValue();
        if (smallestCardValue == 10 && highestCardValue == 14 && areCardsOfTheSameSuit) {
            return new RankedPlayerCardDeck(10, new ArrayList<>(), new ArrayList<>(), playerCardDeck);
        }

        /*
        Checks whether all cards have consecutive values
        */
        areCardsOfConsecutiveValues = smallestCardValue + cardsList.size() - 1 == highestCardValue;

        /*
        Checks if it's a Straight Flush
        */
        if (areCardsOfTheSameSuit && areCardsOfConsecutiveValues) {
            return new RankedPlayerCardDeck(9, new ArrayList<>(), new ArrayList<>(), playerCardDeck);
        }
        /*
        Checks if it's a Four of a Kind
        Same value reappears 4 times in a CardDeck
         */

        if (cardValueWithAmountOfAppearances.containsValue(4)) {
            return new RankedPlayerCardDeck(8, new ArrayList<>(), new ArrayList<>(), playerCardDeck);

        }

        /*
        Checks if it's a full house.
        It's a full house if it found 2 values reappearing (size = 2) and one of those has appeared 3 times (containsValue(3))
        In a case its only 2 reappearing values, it's Two pairs.
         */
        if (cardValueWithAmountOfAppearances.size() == 2 && cardValueWithAmountOfAppearances.containsValue(3)) {
            return new RankedPlayerCardDeck(7, new ArrayList<>(), new ArrayList<>(), playerCardDeck);

        }
        /*
        Checks if it's a Flush
        */
        if (areCardsOfTheSameSuit) {
            return new RankedPlayerCardDeck(6, new ArrayList<>(), new ArrayList<>(), playerCardDeck);

        }
        /*
        Checks if it's a Straight
        */
        if (areCardsOfConsecutiveValues) {
            return new RankedPlayerCardDeck(5, new ArrayList<>(), new ArrayList<>(), playerCardDeck);

        }
        /*
        Checks if it's a Three of a Kind
        */
        if (cardValueWithAmountOfAppearances.containsValue(3)) {
            return new RankedPlayerCardDeck(4, new ArrayList<>(), new ArrayList<>(), playerCardDeck);

        }
        /*
        Checks if it's Two Pairs
        */
        if (cardValueWithAmountOfAppearances.size() == 2) {
            return new RankedPlayerCardDeck(3, new ArrayList<>(), new ArrayList<>(), playerCardDeck);
        }
        /*
        Checks if it's One Pair
        */
        if (cardValueWithAmountOfAppearances.size() != 0) {
            return new RankedPlayerCardDeck(2, new ArrayList<>(), new ArrayList<>(), playerCardDeck);

        }
        /*
        Returns 1 if it's a High Card
        */
        return new RankedPlayerCardDeck(1, new ArrayList<>(), new ArrayList<>(), playerCardDeck);

    }
}
